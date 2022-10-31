package ua.goit.jdbc.command;

import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.math.BigDecimal;
import java.sql.*;

public class ProjectCost implements Command {
    public static final String PROJECT_COST = "cost";
    private Integer projectId = -1;
//    DatabaseManagerConnector connector;
    Connection connection;
    private final View view;

    private static final ProjectService projectService = ProjectService.getInstance();
    private BigDecimal projectCost;
    public ProjectCost(View view, Connection connection) {
        this.connection = connection;
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(PROJECT_COST);
    }
    @Override
    public void execute() {
        projectCost = projectService.getProjectCost(connection);
    }
    public BigDecimal getProjectCost() {
        return projectCost;
    }
}
