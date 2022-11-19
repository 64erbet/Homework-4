package ua.goit.jdbc.command.crud.update;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.service.CompanyServise;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class UpdateCompany implements Command {
    public static final String UPDATE_COMPANY = "u_c";
    View view;
    Connection connection;
    private static final CompanyServise companyServise = CompanyServise.getInstance();

    Boolean rezUpdateCompany = false;

    public UpdateCompany(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(UPDATE_COMPANY));
    }

    @Override
    public void execute() {
        rezUpdateCompany = companyServise.update(connection);
        System.out.println("rezUpdateCompany (from UpdateCompany) = " + rezUpdateCompany);
    }
}
