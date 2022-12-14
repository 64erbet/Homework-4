package ua.goit.jdbc;

import ua.goit.jdbc.command.*;
import ua.goit.jdbc.command.crud.create.CreateCompany;
import ua.goit.jdbc.command.crud.create.CreateProject;
import ua.goit.jdbc.command.crud.delete.DeleteCompany;
import ua.goit.jdbc.command.crud.delete.DeleteProject;
import ua.goit.jdbc.command.crud.read.ReadCompany;
import ua.goit.jdbc.command.crud.read.ReadProject;
import ua.goit.jdbc.command.crud.update.UpdateCompany;
import ua.goit.jdbc.command.crud.update.UpdateProject;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.controller.Controller;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        Connection connection = null;
        try {
            connection = DatabaseManagerConnector.getConnection();
        } catch (SQLException e) {
            System.out.println("!!! Исключение при создании конекшена. \nПерехватили и печатаем стектрейс");
            e.printStackTrace();
        }

        List<Command> commands = new ArrayList<>();

        commands.add(new Exit(view));
        commands.add(new Help(view));

        commands.add(new ProjectCost(view, connection));
        commands.add(new ProjectDevelopersList(view, connection));
        commands.add(new JavaDevelopersList(view, connection));
        commands.add(new MiddleDevelopersList(view, connection));
        commands.add(new FormattedProjectsList(view, connection));

        commands.add(new CreateProject(view, connection));
        commands.add(new ReadProject(view, connection));
        commands.add(new UpdateProject(view, connection));
        commands.add(new DeleteProject(view, connection));

        commands.add(new CreateCompany(view, connection));
        commands.add(new ReadCompany(view, connection));
        commands.add(new UpdateCompany(view, connection));
        commands.add(new DeleteCompany(view, connection));

        Controller controller = new Controller(view, commands);
        controller.run();
    }
}
