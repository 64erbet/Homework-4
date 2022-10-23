package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.model.dao.ProjectDao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class ProjectRepository {
    public ProjectRepository() {
    }

//************************ CRUD ******************************************************
    public ProjectDao create(ProjectDao projectDao, Connection connection) {
        String INSERT_PROJECT = "INSERT INTO public.projects (projectname, " +
                "customerid, createdate, reportdate) " +
                "VALUES (?, ?, ?, ?) "
//                + "RETURNING *"
                ;

        try (var statement = connection.prepareStatement(INSERT_PROJECT,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, projectDao.getProjectNameDAO());
            statement.setInt(2, projectDao.getCustomerIdDAO());
            statement.setDate(3, Date.valueOf(projectDao.getCreateDateDAO()));
            statement.setDate(4, Date.valueOf(projectDao.getReportDateDAO()));

            int i = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            Integer projectId = resultSet.getObject(1, Integer.class);
            projectDao.setProjectId(projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (projectDao);
    }

    public ProjectDao readById(Integer projectId, Connection connection) {
        String READ_PROJECT = "SELECT * " +
                                "FROM projects " +
                                "WHERE projectid = ?"
                ;

        ProjectDao projectDao = new ProjectDao();

        try (var statement = connection.prepareStatement(READ_PROJECT,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, projectId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            projectDao.setProjectId(resultSet.getObject(1, Integer.class));
            projectDao.setProjectNameDAO(resultSet.getObject(2, String.class));
            projectDao.setCustomerIdDAO(resultSet.getObject(3, Integer.class));
            projectDao.setCreateDateDAO(resultSet.getObject(4, LocalDate.class));
            projectDao.setReportDateDAO(resultSet.getObject(5, LocalDate.class));

            System.out.println("projectDao = " + projectDao);
            return (projectDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (null);
    }

    public boolean updateById(Integer projectId, String newProjectName, Connection connection) {
        String UPDATE_PROJECT = "UPDATE projects " +
                "SET projectname = ? " +
                "WHERE projectid = ? " +
                ";";
        Integer kolUpdatedRows = 0;

        try (var statement = connection.prepareStatement(UPDATE_PROJECT,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, newProjectName);
            statement.setInt(2, projectId);

            kolUpdatedRows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (kolUpdatedRows > 0) {
            System.out.println("ВСЕ УСПЕШНО ИЗМЕНЕНО!");
            return (true);
        } else {
            return (false);
        }
    }

    public boolean deleteById(Integer projectId, Connection connection) {
        String DELETE_PROJECT = "DELETE FROM projects " +
                "WHERE projectid = ? "
                ;
        Integer kolUpdatedRows = 0;

        try (var statement = connection.prepareStatement(DELETE_PROJECT,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, projectId);

            kolUpdatedRows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (kolUpdatedRows > 0) {
            System.out.println("ВСЕ УСПЕШНО УДАЛЕНО!");
            return (true);
        } else {
            return (false);
        }
    }

//**************************************************************************************
    //**********************************************************************************
    public BigDecimal getCostOfProjectWithId(Integer id, Connection connection) {
        String QUERY = "SELECT p.projectid, SUM(d.salary) "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON d.developerid = dp.dp_developer_id "
                + "WHERE p.projectid = "
                + id
                + " "
                + "GROUP BY p.projectid"
                ;
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QUERY);
            resultSet.next();

            return (resultSet.getObject(2, BigDecimal.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (null);
    }
    //**********************************************************************************
    public String getProjectWithIdDevs(Integer projectId, Connection connection) {
        String QUERY = "SELECT p.projectid, STRING_AGG(d.firstname, ', ') "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON dp.dp_developer_id = d.developerid "
                + "WHERE p.projectid = "
                + projectId
                + " "
                + "GROUP BY p.projectid"
                ;

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QUERY);
            resultSet.next();
            return (resultSet.getObject(2, String.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (null);
    }
    //**********************************************************************************
    public String getAllJavaDevs(Connection connection) {
        String JAVA_DEVS_QUERY = "SELECT STRING_AGG(d.firstname, ', ') "
                + "FROM developers d "
                + "LEFT JOIN developers_skills ds "
                + "ON d.developerid = ds.ds_developer_id "
                + "LEFT JOIN skills s "
                + "ON s.skillid = ds.ds_skill_id "
                + "WHERE s.skillname = 'Java'"
                ;

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(JAVA_DEVS_QUERY);
            resultSet.next();

            return (resultSet.getObject(1, String.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (null);
    }
    //**********************************************************************************
    public String getMiddleDevelopersList(Connection connection) {
        String MIDDLE_DEVS_QUERY = "SELECT STRING_AGG(DISTINCT d.firstname, ', ') "
                + "FROM skills s "
                + "LEFT JOIN developers_skills ds "
                + "ON s.skillid = ds.ds_skill_id "
                + "LEFT JOIN developers d "
                + "ON d.developerid = ds.ds_developer_id "
                + "WHERE s.skilllavel = 'Middle'"
                ;

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVS_QUERY);
            resultSet.next();

            return (resultSet.getObject(1, String.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (null);
    }
    //**********************************************************************************
    public String getFormattedProjectList(Connection connection) {
        String rezultat = "";
        String MIDDLE_DEVS_QUERY = "SELECT p.projectid, p.createdate, p.reportdate, p.projectname, COUNT(d.firstname)  "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON dp.dp_developer_id = d.developerid "
                + "GROUP BY p.projectname, p.createdate, p.projectid"
                ;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVS_QUERY);
            while (resultSet.next()) {
                Integer projectId = resultSet.getInt(1);
                Date createDate = resultSet.getObject(2, Date.class);
                Date reportDate = resultSet.getObject(3, Date.class);
                String projectName = resultSet.getObject(4, String.class);
                Integer devsCount = resultSet.getInt(5);
                rezultat += "\t".repeat(2)
                        + projectId
                        + "\t".repeat(3)
                        + createDate.toString()
                        + "\t".repeat(2)
                        + reportDate.toString()
                        + " \t".repeat(2)
                        + projectName
                        + " ".repeat(22 - projectName.length())
                        + devsCount
                        + "\n"
                ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rezultat);
    }
}
