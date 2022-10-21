package ua.goit.jdbc.command;

public interface Command {
    boolean canExecute(String input);

    void execute();
}
