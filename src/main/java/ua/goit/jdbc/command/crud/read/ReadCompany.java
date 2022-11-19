package ua.goit.jdbc.command.crud.read;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.service.CompanyServise;
import ua.goit.jdbc.view.View;

import java.sql.Connection;

public class ReadCompany implements Command {
    public static final String READ_COMPANY = "r_c";
    View view;
    Connection connection;
    private static final CompanyServise companyServise = CompanyServise.getInstance();

    private CompanyDao readedCompanyDao;

    public ReadCompany(View view, Connection connection) {
        this.view = view;
        this.connection = connection;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(READ_COMPANY));
    }

    @Override
    public void execute() {
        readedCompanyDao = companyServise.read(connection);
        System.out.println("readedCompanyDao (from ReadCompany) = " + readedCompanyDao);
    }
    public CompanyDao getReadedCompanyDao() {
        return readedCompanyDao;
    }
}
