//package ua.goit.jdbc.command.crud.create;
//
//import ua.goit.jdbc.command.Command;
//import ua.goit.jdbc.model.dao.CompanyDao;
//import ua.goit.jdbc.model.dao.CustomerDao;
//import ua.goit.jdbc.model.dao.ProjectDao;
//import ua.goit.jdbc.service.CompanyServise;
//import ua.goit.jdbc.service.ProjectService;
//import ua.goit.jdbc.view.View;
//
//import java.sql.Connection;
//
//public class CreateCustomer implements Command {
//
//    public static final String CREATE_CUSTOMER = "c_cu";
//
//    View view;
//    Connection connection;
//    private static final CustomerServise customerServise = CustomerServise.getInstance();
//
//    private CustomerDao createdCustomer;
//
//    public CreateCustomer(View view, Connection connection) {
//        this.view = view;
//        this.connection = connection;
//    }
//
//    @Override
//    public boolean canExecute(String input) {
////        return false;
//        return (input.equalsIgnoreCase(CREATE_CUSTOMER));
//    }
//
//    @Override
//    public void execute() {
//        createdCustomer = customerServise.create(connection);
//        if (createdCustomer != null) {
//            System.out.println("Успешно создали нового пользователя!!!");
//        } else {
//            System.out.println("Проблемы при создании пользователя");
//        }
//        System.out.println("createdCustomer (from CreateCustomer):\n" + createdCustomer);
//    }
//
//    public CustomerDao getCreatedCustomer() {
//        return createdCustomer;
//    }
//}
//
//
