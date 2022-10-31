package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.DeveloperDao;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectService {
    private View view = new Console(new Scanner(System.in));
    private static final ProjectService PROJECT_SERVICE = new ProjectService();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    ProjectRepository projectRepository = new ProjectRepository();

    private ProjectService() {

    }

    public static ProjectService getInstance() {
        return PROJECT_SERVICE;
    }


//***************** CRUD *****************************************************************

    public ProjectDao create(Connection connection) {
        int customersCount = projectRepository.getCustomersCount(connection);

        view.write("Введите название проекта");
        var pName = view.read();
        view.write("Введите ПОРЯДКОВЫЙ НОМЕР заказчика проекта (от 1 до " + customersCount + ")");
        var pCustId = view.read();
        view.write("Введите дату создания проекта в формате \"d-MM-yyyy\"");
        LocalDate pCreateDate = LocalDate.parse(view.read(), formatter);
        view.write("Введите дату сдачи проекта в формате \"d-MM-yyyy\"");
        LocalDate pReportDate = LocalDate.parse(view.read(), formatter);
        while (pReportDate.isBefore(pCreateDate) ||
                pReportDate.equals(pCreateDate)) {
            view.write("Вы ввели некорректную дату сдачи проекта! Она должна быть " +
                    "ПОСЛЕ даты его создания" +
                    "\nПопробуйте еще раз! Ввести ДАТУ СДАЧИ ПРОЕКТА");
            pReportDate = LocalDate.parse(view.read(), formatter);
        }

//        if(pReportDate.isBefore(pCreateDate))

        ProjectDao projectDao = new ProjectDao(null, pName, Integer.parseInt(pCustId),
                pCreateDate, pReportDate);
        //*******************************************************************************

        //*******************************************************************************
        ProjectDao projectDaoWithId = projectRepository.create(projectDao, connection);

        System.out.println("!!! СОЗДАЛИ УСПЕШНО. \n" + projectDaoWithId.toString());
        return (projectDaoWithId);
    }

    public ProjectDao read(Connection connection) {
        view.write("Введите пожалуйста id проэкта, который Вы хотите прочитать?");
        String readingProjectId = view.read();

        return (projectRepository.readById(Integer.parseInt(readingProjectId), connection));
    }


    public ProjectDao update(Connection connection) {
        view.write("Введите пожалуйста id проэкта, который Вы хотите проапдейтить?");
        Integer projectToUpdateId = Integer.parseInt(view.read());
        view.write("Введите пожалуйста НОВОЕ ИМЯ проэкта, который Вы хотите проапдейтить?");
        String newProjectName = view.read();

        ProjectDao NEW_projectDao = projectRepository.updateById(projectToUpdateId, newProjectName, connection);
        System.out.println("NEW_projectDao = " + NEW_projectDao);
        return NEW_projectDao;
    }

    public boolean delete(Connection connection) {
        view.write("Вашему вниманию id ВСЕХ существующих проектов");
        String allProjectsIdsString = "";
        ArrayList<Integer> allProjectsIds = projectRepository.getProjectsIds(connection);
        for (int i = 0; i < allProjectsIds.size(); i++) {
            if (i != allProjectsIds.size()-1) {
                allProjectsIdsString += allProjectsIds.get(i) + ", ";
            } else {
                allProjectsIdsString += allProjectsIds.get(i);
            }
        }
        view.write(allProjectsIdsString);

        Integer projectToDeleteId;
        boolean idInList = false;
        do {
            view.write("Введите пожалуйста id проэкта, который Вы хотите безжалостно убить?");

            projectToDeleteId = Integer.parseInt(view.read());

            for (Integer i : allProjectsIds) {
                if (i == projectToDeleteId) {
                    idInList = true;
                    break;
                }
            }
            view.write("Этот номер проекта НЕ ВАЛИДНЫЙ");
        } while (!idInList);

        return (projectRepository.deleteById(projectToDeleteId, connection));
    }

//*************************************************************************************

    public BigDecimal getProjectCost(Connection connection) {
        view.write("Введите пожалуйста id проекта, сумму которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        BigDecimal projectCost = projectRepository.getCostOfProjectWithId(enteredProjectId, connection);

        view.write("Проект номер " + enteredProjectId + " стоит "
                    + projectCost
                    + " долларов");

        return (projectCost);
    }

    public void printProjectDevelopers(Connection connection) {

        view.write("Введите пожалуйста id проекта, список разрабов которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        ArrayList<DeveloperDao> devsList = projectRepository.getProjectWithIdDevs(enteredProjectId, connection);

        view.write("Список разрабов проекта:");
        for (DeveloperDao devDao : devsList) {
            view.write(String.valueOf(devDao));
        }
    }

    public void printProjectJavaDevelopers(Connection connection) {

        ArrayList<DeveloperDao> javaDevsList = projectRepository.getAllJavaDevs(connection);

        view.write("Список JAVA разрабов:");
        for (DeveloperDao devDao : javaDevsList) {
            view.write(String.valueOf(devDao));
        }
    }

    public void printMiddleDevelopersList(Connection connection) {

        ArrayList<DeveloperDao> middleDevsList = projectRepository.getMiddleDevelopersList(connection);

        view.write("Список всех мидлов:");
        for (DeveloperDao devDao : middleDevsList) {
            view.write(String.valueOf(devDao));
        }
    }

    public void printFormattedProjectList(Connection connection) {
//        ArrayList<ProjectDao> formattedProjectList = projectRepository.getFormattedProjectList(connection);
        String formattedProjectList = projectRepository.getFormattedProjectList(connection);
        view.write("Форматированный список проектов: \n" +
//                "Порядковый номер   Дата создания   Дата сдачи      Название      Колличество разрабов");
                "Дата создания       Название проекта      Количество разработчиков на этом проекте\n"
                + formattedProjectList);
        //*************************************************************************
//        for (ProjectDao projectDao : formattedProjectList) {
//            view.write("\t".repeat(2)
//                    + projectDao.getProjectId()
//                    + "\t".repeat(3)
//                    + projectDao.getCreateDateDAO().toString()
//                    + "\t".repeat(2)
//                    + projectDao.getReportDateDAO().toString()
//                    + " \t".repeat(2)
//                    + projectDao.getProjectNameDAO()
//                    + " ".repeat(22 - projectDao.getProjectNameDAO().length())
//                    + projectDao.getD
//                    + "\n"
//            )
//        }
//        rezultat += "\t".repeat(2)
//                + projectId
//                + "\t".repeat(3)
//                + createDate.toString()
//                + "\t".repeat(2)
//                + reportDate.toString()
//                + " \t".repeat(2)
//                + projectName
//                + " ".repeat(22 - projectName.length())
//                + devsCount
//                + "\n"
//        ;
        //*************************************************************************


    }

//***************************************************************************************
    //****************************
//    public int customersCount(Connection connection) {
//        projectRepository.getCustomersCount(connection);
//    }
    //****************************
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
