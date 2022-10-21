package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaDevelopersList implements Command {

    public static final String JAVA_DEVS_LIST = "j_devs";
//    DatabaseManagerConnector connector;
    private final View view;

    Connection connection;

    private static final ProjectService projectService = ProjectService.getInstance();

//    public JavaDevelopersList(View view, DatabaseManagerConnector databaseManagerConnector) {
    public JavaDevelopersList(View view, Connection connection) {
//        connector = databaseManagerConnector;
        this.connection = connection;
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(JAVA_DEVS_LIST);
    }

    @Override
    public void execute() {
        projectService.printProjectJavaDevelopers();
    }
}
