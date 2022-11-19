package ua.goit.jdbc.command.crud.update;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class UpdateProject implements Command {
    public static final String UPDATE_PROJECT = "u_p";
    View view;
    Connection connection;
    private static final ProjectService projectService = ProjectService.getInstance();

//    private ProjectDao updatedProjectDao;
    Boolean rezUpdateProject = false;

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
        rezUpdateProject = projectService.update(connection);
        System.out.println("rezUpdateProject (from UpdateProject) = " + rezUpdateProject);
    }
}
