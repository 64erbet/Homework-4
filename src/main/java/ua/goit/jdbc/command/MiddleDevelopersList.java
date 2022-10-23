package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MiddleDevelopersList implements Command {
    public static final String MIDDLE_DEVS_LIST = "m_devs";
//    DatabaseManagerConnector connector;
    Connection connection;
    View view;
    private static final ProjectService projectServise = ProjectService.getInstance();

//    public MiddleDevelopersList(View view, DatabaseManagerConnector databaseManagerConnector) {
    public MiddleDevelopersList(View view, Connection connection) {
//        connector = databaseManagerConnector;
        this.connection = connection;
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
//        return false;
        return (input.equalsIgnoreCase(MIDDLE_DEVS_LIST));
    }

    @Override
    public void execute() {
        projectServise.printMiddleDevelopersList(connection);
    }
}
