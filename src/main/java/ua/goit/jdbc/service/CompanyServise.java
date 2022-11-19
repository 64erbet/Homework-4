package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.repository.CompanyRepository;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class CompanyServise {
    private View view = new Console(new Scanner(System.in));
    private static final CompanyServise COMPANY_SERVISE = new CompanyServise();
    CompanyRepository companyRepository = new CompanyRepository();
    private CompanyServise() {
    }

    public static CompanyServise getInstance() {
        return COMPANY_SERVISE;
    }

//***************** CRUD *****************************************************************
    public CompanyDao create(Connection connection) {
        String companyName = null;
        String companyLocation = null;
        try {
            view.write("Введите название компании");
            companyName = view.read();
            view.write("Введите локацию компании. (Желательно в формате \"Страна, Город\")");
            companyLocation = view.read();
        } catch (Exception e) {
            System.out.println("Введены некорректные данные для создания компании!!!!");
        }

        CompanyDao companyDao = new CompanyDao(null, companyName, companyLocation);
        CompanyDao companyDaoWithId = companyRepository.create(companyDao, connection);

        return (companyDaoWithId);
    }

    public CompanyDao read(Connection connection) {
        view.write("Введите пожалуйста id компании, которую Вы хотите прочитать?");
        String readingCompanyId = view.read();
//        System.out.println("companyRepository.readById(Integer.parseInt(readingCompanyId), connection) = " + companyRepository.readById(Integer.parseInt(readingCompanyId), connection));
        return (companyRepository.readById(Integer.parseInt(readingCompanyId), connection));
    }

    public Boolean update(Connection connection) {
        view.write("Введите пожалуйста id компании, которую Вы хотите проапдейтить?");
        Integer companyToUpdateId = Integer.parseInt(view.read());
        view.write("Введите пожалуйста НОВОЕ ИМЯ компании, которую Вы хотите проапдейтить?");
        String newCompanyName = view.read();

//        CompanyDao NEW_companyDao = companyRepository.updateById(companyToUpdateId, newCompanyName, connection);
//        System.out.println("NEW_companyDao = " + NEW_companyDao);
//        return NEW_companyDao;

        return (companyRepository.updateById(companyToUpdateId, newCompanyName, connection));
    }

    public boolean delete(Connection connection) {
        view.write("Вашему вниманию id ВСЕХ существующих компаний");
        String allCompaniesIdsString = "";
        ArrayList<Integer> allCompaniesIds = companyRepository.getCompaniesIds(connection);
        for (int i = 0; i < allCompaniesIds.size(); i++) {
            if (i != allCompaniesIds.size()-1) {
                allCompaniesIdsString += allCompaniesIds.get(i) + ", ";
            } else {
                allCompaniesIdsString += allCompaniesIds.get(i);
            }
        }
        view.write(allCompaniesIdsString);

        Integer companyToDeleteId;
        boolean idInList = false;
        do {
            view.write("Введите пожалуйста id компании (список ВСЕХ компаний - выше), которую Вы хотите безжалостно убить?");

            companyToDeleteId = Integer.parseInt(view.read());

            for (Integer i : allCompaniesIds) {
                if (i == companyToDeleteId) {
                    idInList = true;
                    break;
                }
            }
            view.write("Этот номер компании НЕ ВАЛИДНЫЙ.");
        } while (!idInList);

        return (companyRepository.deleteById(companyToDeleteId, connection));
    }

//****************************************************************************************
}
