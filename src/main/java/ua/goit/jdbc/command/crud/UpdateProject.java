package ua.goit.jdbc.command.crud;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class UpdateProject implements Command {
    public static final String UPDATE_PROJECT = "u_p";
    View view;
    Connection connection;
    private static final ProjectService projectService = ProjectService.getInstance();

    public UpdateProject(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(UPDATE_PROJECT));
    }

    @Override
    public void execute() {
        projectService.update();
    }
}
