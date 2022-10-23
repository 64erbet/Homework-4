package ua.goit.jdbc.command.crud;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class DeleteProject implements Command {

    public static final String DELETE_PROJECT = "d_p";
    View view;
    Connection connection;
    public static final ProjectService projectService = ProjectService.getInstance();

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
        projectService.delete(connection);
    }
}
