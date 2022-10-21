package ua.goit.jdbc.command;

import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class ProjectDevelopersList implements Command {

    public static final String PROJECT_DEVS_LIST = "devs";
    Connection connection;
    private final View view;

    private static final ProjectService projectService = ProjectService.getInstance();

    public ProjectDevelopersList(View view, Connection connection) {
        this.connection = connection;
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(PROJECT_DEVS_LIST);
    }
    @Override
    public void execute() {
        projectService.printProjectDevelopers();
    }
}
