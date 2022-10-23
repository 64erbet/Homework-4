package ua.goit.jdbc.command;

import com.sun.jdi.connect.Connector;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.*;

public class FormattedProjectsList implements Command {
    public static final String FORMATTED_PROJECTS_LIST = "f_list";
//    DatabaseManagerConnector connector;
    Connection connection;
    View view;
    private static final ProjectService projectService = ProjectService.getInstance();

//    public FormattedProjectsList(View view, DatabaseManagerConnector databaseManagerConnector) {
    public FormattedProjectsList(View view, Connection connection) {
//        connector = databaseManagerConnector;
        this.connection = connection;
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(FORMATTED_PROJECTS_LIST));
    }

    @Override
    public void execute() {
        projectService.printFormattedProjectList(connection);
    }
}
