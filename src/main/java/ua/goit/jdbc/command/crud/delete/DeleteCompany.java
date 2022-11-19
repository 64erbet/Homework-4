package ua.goit.jdbc.command.crud.delete;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.CompanyServise;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class DeleteCompany implements Command {
    public static final String DELETE_COMPANY = "d_c";
    View view;
    Connection connection;
    public static final CompanyServise companyServise = CompanyServise.getInstance();

    boolean deleteCompany;

    public DeleteCompany(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(DELETE_COMPANY));
    }

    @Override
    public void execute() {
        boolean deleteCompany = companyServise.delete(connection);
        if (deleteCompany){
            System.out.println("Успешно удалили компанию!!!");
        } else {
            System.out.println("Проблемы при удалении компании");
        }
    }
}
