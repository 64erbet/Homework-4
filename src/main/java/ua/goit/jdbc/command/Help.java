package ua.goit.jdbc.command;

import ua.goit.jdbc.view.View;

import static ua.goit.jdbc.command.Exit.EXIT;
import static ua.goit.jdbc.command.ProjectCost.PROJECT_COST;
import static ua.goit.jdbc.command.ProjectDevelopersList.PROJECT_DEVS_LIST;
import static ua.goit.jdbc.command.JavaDevelopersList.JAVA_DEVS_LIST;
import static ua.goit.jdbc.command.MiddleDevelopersList.MIDDLE_DEVS_LIST;
import static ua.goit.jdbc.command.FormattedProjectsList.FORMATTED_PROJECTS_LIST;
import static ua.goit.jdbc.command.crud.create.CreateCompany.CREATE_COMPANY;
import static ua.goit.jdbc.command.crud.create.CreateProject.CREATE_PROJECT;
import static ua.goit.jdbc.command.crud.delete.DeleteCompany.DELETE_COMPANY;
import static ua.goit.jdbc.command.crud.delete.DeleteProject.DELETE_PROJECT;
import static ua.goit.jdbc.command.crud.read.ReadCompany.READ_COMPANY;
import static ua.goit.jdbc.command.crud.read.ReadProject.READ_PROJECT;
import static ua.goit.jdbc.command.crud.update.UpdateCompany.UPDATE_COMPANY;
import static ua.goit.jdbc.command.crud.update.UpdateProject.UPDATE_PROJECT;

//import static ua.goit.jdbc.command.ProjectDevelopersList.FORMATTED_PROJECTS_LIST;

public class Help implements Command{
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter \"%s\" to see all command", HELP));
        view.write(String.format("Enter \"%s\" to exit program", EXIT));
        view.write(String.format("Enter \"%s\" to find project cost (amount of salaries of project developers)", PROJECT_COST));
        view.write(String.format("Enter \"%s\" command to get project developers list", PROJECT_DEVS_LIST));
        view.write(String.format("Enter \"%s\" command to get project JAVA developers list", JAVA_DEVS_LIST));
        view.write(String.format("Enter \"%s\" command to get project MIDDLE developers list", MIDDLE_DEVS_LIST));
        view.write(String.format("Enter \"%s\" command to get FORMATTED projects list", FORMATTED_PROJECTS_LIST));

        view.write(String.format("Enter \"%s\" command to CREATE project", CREATE_PROJECT));
        view.write(String.format("Enter \"%s\" command to CREATE project", CREATE_COMPANY));

        view.write(String.format("Enter \"%s\" command to READ project", READ_PROJECT));
        view.write(String.format("Enter \"%s\" command to READ project", READ_COMPANY));

        view.write(String.format("Enter \"%s\" command to UPDATE project", UPDATE_PROJECT));
        view.write(String.format("Enter \"%s\" command to UPDATE project", UPDATE_COMPANY));

        view.write(String.format("Enter \"%s\" command to DELETE project", DELETE_PROJECT));
        view.write(String.format("Enter \"%s\" command to DELETE project", DELETE_COMPANY));
    }
}
