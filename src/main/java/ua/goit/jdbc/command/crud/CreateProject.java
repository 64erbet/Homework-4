package ua.goit.jdbc.command.crud;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class CreateProject implements Command {

    public static final String CREATE_PROJECT = "c_p";

    View view;
    Connection connection;
    private static final ProjectService projectService = ProjectService.getInstance();

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
        projectService.create(connection);
    }
}
