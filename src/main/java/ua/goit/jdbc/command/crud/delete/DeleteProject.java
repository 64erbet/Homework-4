package ua.goit.jdbc.command.crud.delete;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class DeleteProject implements Command {

    public static final String DELETE_PROJECT = "d_p";
    View view;
    Connection connection;
    public static final ProjectService projectService = ProjectService.getInstance();

    boolean deleteProject;

    public DeleteProject(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(DELETE_PROJECT));
    }

    @Override
    public void execute() {
        boolean deleteProject = projectService.delete(connection);
        if (deleteProject){
            System.out.println("Успешно удалили проект!!!");
        } else {
            System.out.println("Проблемы при удалении проекта");
        }
    }
}
