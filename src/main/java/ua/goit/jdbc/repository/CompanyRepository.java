package ua.goit.jdbc.repository;

import ua.goit.jdbc.exceprtions.ExitException;
import ua.goit.jdbc.model.dao.CompanyDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompanyRepository {
    public CompanyRepository() {
    }

//************************ CRUD ******************************************************
    public CompanyDao create(CompanyDao companyDao, Connection connection) {

        String INSERT_COMPANY = "INSERT INTO public.companies (companyname, " +
                "companylocation) " +
                "VALUES (?, ?) "
                ;

        try (var statement = connection.prepareStatement(INSERT_COMPANY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, companyDao.getCompName());
            statement.setString(2, companyDao.getCompLocation());

            int i = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            Integer companyId = resultSet.getObject(1, Integer.class);
            companyDao.setCompId(companyId);
        } catch (SQLException e) {
            throw new ExitException("Exception on company creation\n" +
                    "Enter \"Help\" to see all commands");
        }
        return (companyDao);
    }

    public CompanyDao readById(Integer companyId, Connection connection) {
        String READ_COMPANY = "SELECT * " +
                                "FROM companies " +
                                "WHERE companyid = ?"
                            ;

        CompanyDao companyDao = null;
        try (var statement = connection.prepareStatement(READ_COMPANY,
                    Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, companyId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            companyDao = new CompanyDao();
            companyDao.setCompId(resultSet.getObject(1, Integer.class));
            companyDao.setCompName(resultSet.getObject(2, String.class));
            companyDao.setCompLocation(resultSet.getObject(3, String.class));
        } catch (SQLException e) {
            System.out.println("There is NO company with this id\n" +
                    "Enter \"Help\" to see all commands");
        }
        return (companyDao);
    }

    public Boolean updateById(Integer companyId, String newCompanyName, Connection connection) {
        String UPDATE_COMPANY = "UPDATE companies " +
                "SET companyname = ? " +
                "WHERE companyid = ? "
                ;

        Boolean rez_update_company = false;
        try (var statement = connection.prepareStatement(UPDATE_COMPANY)) {

            statement.setString(1, newCompanyName);
            statement.setInt(2, companyId);

            if (statement.executeUpdate() > 0) {
                rez_update_company = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception on company updating\n" +
                    "Enter \"Help\" to see all commands");
        }
        return (rez_update_company);
    }

    public boolean deleteById(Integer companyId, Connection connection) {
        String DELETE_COMPANY = "DELETE FROM companies " +
                "WHERE companyid = ? "
                ;

        Integer kolUpdatedRows = 0;

        try (var statement = connection.prepareStatement(DELETE_COMPANY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, companyId);

            kolUpdatedRows = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception on company deleting\n" +
                    "Enter \"Help\" to see all commands");
        }
        if (kolUpdatedRows > 0) {
            System.out.println("ВСЕ УСПЕШНО УДАЛЕНО!");
            return (true);
        } else {
            return (false);
        }
    }
//************************************************************************************
    public ArrayList<Integer> getCompaniesIds(Connection connection) {
        ArrayList<Integer> cIdsList = new ArrayList<>();
        String companiesIdsQuery = "SELECT c.companyid " +
                "FROM companies c " +
                ";";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(companiesIdsQuery);

            while (resultSet.next()) {
                cIdsList.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new ExitException("Exception when method \"getCompaniesIds()\" executing");
        }
        return (cIdsList);
    }
}
