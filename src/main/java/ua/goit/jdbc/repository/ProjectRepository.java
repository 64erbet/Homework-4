package ua.goit.jdbc.repository;

import ua.goit.jdbc.exceprtions.ExitException;
import ua.goit.jdbc.model.dao.DeveloperDao;
import ua.goit.jdbc.model.dao.ProjectDao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
            throw new ExitException("Exception on project creation");
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

            System.out.println("FROM REPOSITORY !!!! \nprojectDao = " + projectDao);
            return (projectDao);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception on reading project");
        }
    }

    public ProjectDao updateById(Integer projectId, String newProjectName, Connection connection) {
        String UPDATE_PROJECT = "UPDATE projects " +
                "SET projectname = ? " +
                "WHERE projectid = ? " +
                "RETURNING * " +
                "\n;";

        ProjectDao projectDao = new ProjectDao();
        try (var statement = connection.prepareStatement(UPDATE_PROJECT)){

            statement.setString(1, newProjectName);
            statement.setInt(2, projectId);

            ResultSet resultSetUpdate = statement.executeQuery();

            resultSetUpdate.next();

            projectDao.setProjectId(resultSetUpdate.getInt(1));
            projectDao.setProjectNameDAO(resultSetUpdate.getObject(2, String.class));
            projectDao.setCustomerIdDAO(resultSetUpdate.getObject(3, Integer.class));
            projectDao.setCreateDateDAO(resultSetUpdate.getObject(4, Date.class).toLocalDate());
            projectDao.setReportDateDAO(resultSetUpdate.getObject(5, Date.class).toLocalDate());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception on project updating");
        }
        return (projectDao);
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
            throw new ExitException("Exception on project deleting");
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
        String Cost_QUERY = "SELECT p.projectid, SUM(d.salary) "
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

            ResultSet resultSet = statement.executeQuery(Cost_QUERY);
            resultSet.next();

            return (resultSet.getObject(2, BigDecimal.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getCostOfProjectWithId()\" executing");
        }
    }
    //**********************************************************************************
    public ArrayList<DeveloperDao> getProjectWithIdDevs(Integer projectId, Connection connection) {
        String QUERY = "SELECT d.developerid, d.firstname, d.gender, d.age, "
                + "d.companyid, d.salary "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON dp.dp_developer_id = d.developerid "
                + "WHERE p.projectid = "
                + projectId
                ;
        ArrayList<DeveloperDao> devDaoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                DeveloperDao developerDao = new DeveloperDao();

                developerDao.setDevId(resultSet.getInt(1));
                developerDao.setDevName(resultSet.getObject(2, String.class));
                developerDao.setDevGen(resultSet.getObject(3, String.class));
                developerDao.setDevAge(resultSet.getInt(4));
                developerDao.setCompanyId(resultSet.getInt(5));
                developerDao.setSalary(resultSet.getObject(6, BigDecimal.class));

                devDaoList.add(developerDao);
            }

            return (devDaoList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getProjectWithIdDevs()\" executing");
        }
    }
    //**********************************************************************************
    public ArrayList<DeveloperDao> getAllJavaDevs(Connection connection) {
        String JAVA_DEVS_QUERY = "SELECT d.developerid, d.firstname, "
                    + "d.gender, d.age, d.companyid, d.salary "
                + "FROM developers d "
                + "LEFT JOIN developers_skills ds "
                + "ON d.developerid = ds.ds_developer_id "
                + "LEFT JOIN skills s "
                + "ON s.skillid = ds.ds_skill_id "
                + "WHERE s.skillname = 'Java'"
        ;

        ArrayList<DeveloperDao> javaDevDaoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(JAVA_DEVS_QUERY);

            while (resultSet.next()) {
                DeveloperDao javaDeveloperDao = new DeveloperDao();

                javaDeveloperDao.setDevId(resultSet.getInt(1));
                javaDeveloperDao.setDevName(resultSet.getObject(2, String.class));
                javaDeveloperDao.setDevGen(resultSet.getObject(3, String.class));
                javaDeveloperDao.setDevAge(resultSet.getInt(4));
                javaDeveloperDao.setCompanyId(resultSet.getInt(5));
                javaDeveloperDao.setSalary(resultSet.getObject(6, BigDecimal.class));

                javaDevDaoList.add(javaDeveloperDao);
            }

            return (javaDevDaoList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getAllJavaDevs()\" executing");
        }
    }
    //**********************************************************************************
    public ArrayList<DeveloperDao> getMiddleDevelopersList(Connection connection) {
        String MIDDLE_DEVS_QUERY = "SELECT d.developerid, d.firstname, "
                    + "d.gender, d.age, d.companyid, d.salary "
                + "FROM skills s "
                + "LEFT JOIN developers_skills ds "
                + "ON s.skillid = ds.ds_skill_id "
                + "LEFT JOIN developers d "
                + "ON d.developerid = ds.ds_developer_id "
                + "WHERE s.skilllavel = 'Middle'"
                ;

        ArrayList<DeveloperDao> middleDevDaoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVS_QUERY);
            while (resultSet.next()) {
                DeveloperDao middleDeveloperDao = new DeveloperDao();

                middleDeveloperDao.setDevId(resultSet.getInt(1));
                middleDeveloperDao.setDevName(resultSet.getObject(2, String.class));
                middleDeveloperDao.setDevGen(resultSet.getObject(3, String.class));
                middleDeveloperDao.setDevAge(resultSet.getInt(4));
                middleDeveloperDao.setCompanyId(resultSet.getInt(5));
                middleDeveloperDao.setSalary(resultSet.getObject(6, BigDecimal.class));

                middleDevDaoList.add(middleDeveloperDao);
            }

            return (middleDevDaoList);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getMiddleDevelopersList()\" executing");
        }
//        return (null);
    }
    //**********************************************************************************
    public String getFormattedProjectList(Connection connection) {
        String rezultat = "";
        String MIDDLE_DEVS_QUERY = "SELECT p.projectid, p.createdate, p.reportdate, "
                    + "p.projectname, COUNT(d.developerid), c.customerid "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON dp.dp_developer_id = d.developerid "
                + "LEFT JOIN customers_projects cp "
                + "ON p.projectid = cp.cuspr_project_id "
                + "LEFT JOIN customers c "
                + "ON cp.cuspr_customer_id = c.customerid "
                + "GROUP BY p.projectname, p.createdate, p.projectid, c.customerid"
        ;

        ArrayList<ProjectDao> arrayListProjectDao = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVS_QUERY);
            while (resultSet.next()) {

                ProjectDao projectDao = new ProjectDao();

                projectDao.setProjectId(resultSet.getInt(1));
                projectDao.setProjectNameDAO(resultSet.getObject(4, String.class));
                projectDao.setCustomerIdDAO(resultSet.getInt(6));
                projectDao.setCreateDateDAO(resultSet.getObject(2, LocalDate.class));
                projectDao.setReportDateDAO(resultSet.getObject(3, LocalDate.class));

                arrayListProjectDao.add(projectDao);

//                Integer projectId = resultSet.getInt(1);
                Date createDate = resultSet.getObject(2, Date.class);
//                Date reportDate = resultSet.getObject(3, Date.class);
                String projectName = resultSet.getObject(4, String.class);
                Integer devsCount = resultSet.getInt(5);
//                Integer customerId = resultSet.getInt(6);

                rezultat +=
//                        "\t".repeat(2)
//                        + projectId
//                        + "\t".repeat(3)
                        " "
                        + createDate.toString()
                        + "\t".repeat(4)
//                        + reportDate.toString()
//                        + " \t".repeat(2)
                        + projectName
                        + " ".repeat(38 - projectName.length())
                        + devsCount
                        + "\n"
                ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getFormattedProjectList()\" executing");
        }
//        return (arrayListProjectDao);
        return (rezultat);
    }

    public Integer getCustomersCount(Connection connection) {
        Integer rezult = -1;
        String customersCount = "SELECT count(customerid) " +
                "FROM customers"
                + ";"
        ;

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(customersCount);

            resultSet.next();
            rezult = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getCustomersCount()\" executing");
        }
        return (rezult);
    }

//    public String getProjectsIds(Connection connection) {
    public ArrayList<Integer> getProjectsIds(Connection connection) {
//        String rezult = "";
        ArrayList<Integer> pIdsList = new ArrayList<>();
        String projectIdsQuery = "SELECT p.projectid " +
                "FROM projects p " +
                ";";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(projectIdsQuery);

            while (resultSet.next()) {
//                rezult += resultSet.getInt(1) + ", ";
                pIdsList.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExitException("Exception when method \"getProjectsIds()\" executing");
        }
//        return (rezult.substring(0, rezult.length()-2));
        return (pIdsList);
    }
}
