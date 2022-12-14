package ua.goit.jdbc.command.crud.read;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class ReadProject implements Command {
    public static final String READ_PROJECT = "r_p";
    View view;
    Connection connection;
    private static final ProjectService projectService = ProjectService.getInstance();

    private ProjectDao readedProjectDao;

    public ReadProject(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(READ_PROJECT));
    }

    @Override
    public void execute() {
        readedProjectDao = projectService.read(connection);
        System.out.println("readedProjectDao (from ReadProject) = " + readedProjectDao);
    }
    public ProjectDao getReadedProjectDao() {
        return readedProjectDao;
    }
}
