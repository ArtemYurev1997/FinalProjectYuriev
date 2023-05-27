package by.pvt.finalproject;

import controller.AdminController;
import controller.ClientController;
import controller.UserController;
import entities.User;
import enums.Role;
import exceptions.*;

import java.util.Scanner;

public class CurrencyChanger {
    public void process() {
        String programWork = "";
        Scanner scanner = new Scanner(System.in);
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        UserController userController = applicationContext.getUserController();
        ClientController clientController = applicationContext.getClientController();
        AdminController adminController = applicationContext.getAdminController();
        try {
            while (!programWork.equals("Нет")) {
                System.out.println("Выберите действие \n" +
                        "1 - Регистрация пользователя \n" +
                        "2 - Войти в кабинет \n");
                String command = scanner.nextLine();
                if (command.equals("1")) {
                        User user = userController.register(scanner);
                        System.out.println(user);
                } else if (command.equals("2")) {
                    User user = userController.authenticate(scanner);
                    goToMenu(user, adminController, clientController, scanner);
                }
                programWork = scanner.nextLine();
            }
            System.exit(1);
        } catch (ClientException e) {
            scanner.reset();
            process();
        } catch(FileException e) {
            scanner.reset();
            process();
        } catch(BankException e) {
            System.out.println();
            User user = userController.authenticate(scanner);
            goToMenu(user, adminController, clientController, scanner);
        }
        catch(AccountException e) {
            System.out.println();
            User user = userController.authenticate(scanner);
            goToMenu(user, adminController, clientController, scanner);
        }
        catch (ExitException e) {
            process();
        }
    }

    public void goToMenu(User user, AdminController adminController, ClientController clientController, Scanner scanner) {
        if(user.getRole().equals(Role.ADMIN)) {
            adminController.adminProcess(scanner, user);
        }
        else {
            clientController.clientProcess(scanner, user);
        }
    }
}
