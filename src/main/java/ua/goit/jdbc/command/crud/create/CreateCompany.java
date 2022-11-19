package ua.goit.jdbc.command.crud.create;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.service.CompanyServise;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class CreateCompany implements Command {
    public static final String CREATE_COMPANY = "c_c";

    View view;
    Connection connection;
    private static final CompanyServise companyServise = CompanyServise.getInstance();

    private CompanyDao createdCompany;

    public CreateCompany(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
//        return false;
        return (input.equalsIgnoreCase(CREATE_COMPANY));
    }

    @Override
    public void execute() {
        createdCompany = companyServise.create(connection);
        if (createdCompany != null) {
            System.out.println("Успешно создали новую компанию!!!");
        } else {
            System.out.println("Проблемы при создании компании");
        }
        System.out.println("createdCompany (from CreateCompany):\n" + createdCompany);
    }

    public CompanyDao getCreatedCompany() {
        return createdCompany;
    }
}
