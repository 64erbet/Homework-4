package ua.goit.jdbc.command;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.exceprtions.ExitException;
import ua.goit.jdbc.view.View;

public class Exit implements Command {
    public static final String EXIT = "exit";
    public static final String BYE_MESSAGE = "Bye!";

    private final View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(EXIT);
    }

    @Override
    public void execute() {
        view.write(BYE_MESSAGE);
        throw new ExitException();
    }
}
