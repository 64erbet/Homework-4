package ua.goit.jdbc.command.crud;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class CreateProject implements Command {

    public static final String CREATE_PROJECT = "c_p";

    View view;
    Connection connection;
    private static final ProjectService projectService = ProjectService.getInstance();
    private ProjectDao createdProject;

    public CreateProject(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
//        return false;
        return (input.equalsIgnoreCase(CREATE_PROJECT));
    }

    @Override
    public void execute() {
        createdProject = projectService.create(connection);
        if (createdProject != null) {
            System.out.println("Успешно создали новый проект!!!");
        } else {
            System.out.println("Проблемы при создании проета");
        }
    }

    public ProjectDao getCreatedProject() {
        return createdProject;
    }
}
