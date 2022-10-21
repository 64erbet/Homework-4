package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;

import java.math.BigDecimal;
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

    public ProjectDao create() {
        view.write("Введите название проекта");
        var pName = view.read();
        view.write("Введите заказчика проекта");
        var pCustId = view.read();
        view.write("Введите дату создания проекта в формате \"d-MM-yyyy\"");
        var pCreateDate = view.read();
        LocalDate localDate = LocalDate.parse(pCreateDate, formatter);

        ProjectDao projectDao = new ProjectDao(null, pName, Integer.parseInt(pCustId), localDate);
        ProjectDao projectDaoWithId = projectRepository.create(projectDao);

        System.out.println("!!! СОЗДАЛИ УСПЕШНО. \n" + projectDaoWithId.toString());
        return (projectDaoWithId);
    }

    public ProjectDao read() {
        view.write("Введите пожалуйста id проэкта, который Вы хотите прочитать?");
        String readingProjectId = view.read();

        return (projectRepository.readById(Integer.parseInt(readingProjectId)));
    }


    public boolean update() {
        view.write("Введите пожалуйста id проэкта, который Вы хотите проапдейтить?");
        Integer projectToUpdateId = Integer.parseInt(view.read());
        view.write("Введите пожалуйста НОВОЕ ИМЯ проэкта, который Вы хотите проапдейтить?");
        String newProjectName = view.read();

        return (projectRepository.updateById(projectToUpdateId, newProjectName));
    }

    public boolean delete() {
        view.write("Введите пожалуйста id проэкта, который Вы хотите безжалостно убить?");
        Integer projectToDeleteId = Integer.parseInt(view.read());

        return (projectRepository.deleteById(projectToDeleteId));
    }

//*************************************************************************************

    public void printProjectCost() {
        view.write("Введите пожалуйста id проекта, сумму которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        BigDecimal projectCost = projectRepository.getCostOfProjectWithId(enteredProjectId);

        view.write("Проект номер " + enteredProjectId + " стоит "
                    + projectCost
                    + " долларов");
    }

    public void printProjectDevelopers() {

        view.write("Введите пожалуйста id проекта, список разрабов которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        String devsList = projectRepository.getProjectWithIdDevs(enteredProjectId);

        view.write("Список разрабов проекта " + enteredProjectId + ": \n" + devsList);
    }

    public void printProjectJavaDevelopers() {

        String javaDevsList = projectRepository.getAllJavaDevs();

        view.write("Список всех JAVA разрабов: \n" + javaDevsList);
    }

    public void printMiddleDevelopersList() {
        String middleDevsList = projectRepository.getMiddleDevelopersList();
        view.write("Список всех мидлов: \n" + middleDevsList);
    }

    public void printFormattedProjectList() {
        String formattedProjectList = projectRepository.getFormattedProjectList();
        view.write("Форматированный список проектов: \n" +
                "Порядковый номер   Дата создания      Название      Колличество разрабов\n" +
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