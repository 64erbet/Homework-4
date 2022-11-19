package ua.goit.jdbc.controller;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.exceprtions.ExitException;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class Controller {
//    ProjectService projectService = null;
    ProjectService projectService = ProjectService.getInstance();
    View view;
    List<Command> commandList = null;
    public Controller(View view, List<Command> commandList) {
        this.view = view;
        this.commandList = commandList;
    }

    // Принимает из клавы команды пользователя и обрабатывает их (бесконечный цикл
    // while где он ждет команду пользователя. Это есть СУТЬ УРОВНЯ УПРАВЛЕНИЯ)
    // Должен быть список команд из которых он вибирает. Ему передаем этот список команд

    public void run() {
        view.write("Привет :) А не ввести ли Вам \"Help\", чтоб увидеть весь список возможных неприятностей?" +
                "\n!!! НО если Вы гений (знаете все команды напамять) - можно вводить сразу комманду.");
        try {
            execute();
        } catch (ExitException e)
        {
            e.printStackTrace();
        }
    }

    // Получив команду он направляет ее (команду) в УРОВЕНЬ SERVISE (буфер).
    // SERVISE решает как ее выполнить (что кому одтать как результат выполнения).

    private void execute() {
        while(true) {
            String input = view.read();
            boolean inputCorrect = false;
            for(Command command : commandList) {
                if (command.canExecute(input)) {
                    try {
                        command.execute();
                    } catch (NumberFormatException e) {
                        view.write("******** Wrong input ******** ");
                    }
                    inputCorrect = true;
                }
            }
            if(!inputCorrect) {
                view.write("Такой комманды нет. Ведите, пожалуйста HELP чтобы увидеть список комманд");
            }
        }
    }

    // Задача контроллера - получить от пользователя команду (выбрать команду из
    // списка команд) и прокинуть ее на уровень сервиса
    //
//    Модель - это мое
    // Задача сервиса - собрать ProjectDao спросив у пользователя нужные поля (три поля)
    // и вызвать у класса репозиторий метод save(ProjectDao(который он только что собрал
    // по указаниям пользователя))
    //
    //
}
