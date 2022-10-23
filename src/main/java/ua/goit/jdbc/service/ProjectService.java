package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        view.write("Введите название проекта");
        var pName = view.read();
        view.write("Введите заказчика проекта");
        var pCustId = view.read();
        view.write("Введите дату создания проекта в формате \"d-MM-yyyy\"");
//        var pCreateDate = view.read();
        LocalDate pCreateDate = LocalDate.parse(view.read(), formatter);
        view.write("Введите дату сдачи проекта в формате \"d-MM-yyyy\"");
//        var pReportDate = view.read();
        LocalDate pReportDate = LocalDate.parse(view.read(), formatter);

        ProjectDao projectDao = new ProjectDao(null, pName, Integer.parseInt(pCustId),
                pCreateDate, pReportDate);
        ProjectDao projectDaoWithId = projectRepository.create(projectDao, connection);

        System.out.println("!!! СОЗДАЛИ УСПЕШНО. \n" + projectDaoWithId.toString());
        return (projectDaoWithId);
    }

    public ProjectDao read(Connection connection) {
        view.write("Введите пожалуйста id проэкта, который Вы хотите прочитать?");
        String readingProjectId = view.read();

        return (projectRepository.readById(Integer.parseInt(readingProjectId), connection));
    }


    public boolean update(Connection connection) {
        view.write("Введите пожалуйста id проэкта, который Вы хотите проапдейтить?");
        Integer projectToUpdateId = Integer.parseInt(view.read());
        view.write("Введите пожалуйста НОВОЕ ИМЯ проэкта, который Вы хотите проапдейтить?");
        String newProjectName = view.read();

        return (projectRepository.updateById(projectToUpdateId, newProjectName, connection));
    }

    public boolean delete(Connection connection) {
        view.write("Введите пожалуйста id проэкта, который Вы хотите безжалостно убить?");
        Integer projectToDeleteId = Integer.parseInt(view.read());

        return (projectRepository.deleteById(projectToDeleteId, connection));
    }

//*************************************************************************************

    public void printProjectCost(Connection connection) {
        view.write("Введите пожалуйста id проекта, сумму которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        BigDecimal projectCost = projectRepository.getCostOfProjectWithId(enteredProjectId, connection);

        view.write("Проект номер " + enteredProjectId + " стоит "
                    + projectCost
                    + " долларов");
    }

    public void printProjectDevelopers(Connection connection) {

        view.write("Введите пожалуйста id проекта, список разрабов которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        String devsList = projectRepository.getProjectWithIdDevs(enteredProjectId, connection);

        view.write("Список разрабов проекта " + enteredProjectId + ": \n" + devsList);
    }

    public void printProjectJavaDevelopers(Connection connection) {

        String javaDevsList = projectRepository.getAllJavaDevs(connection);

        view.write("Список всех JAVA разрабов: \n" + javaDevsList);
    }

    public void printMiddleDevelopersList(Connection connection) {
        String middleDevsList = projectRepository.getMiddleDevelopersList(connection);
        view.write("Список всех мидлов: \n" + middleDevsList);
    }

    public void printFormattedProjectList(Connection connection) {
        String formattedProjectList = projectRepository.getFormattedProjectList(connection);
        view.write("Форматированный список проектов: \n" +
                "Порядковый номер   Дата создания   Дата сдачи      Название      Колличество разрабов\n" +
                formattedProjectList);
    }

//***************************************************************************************
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
