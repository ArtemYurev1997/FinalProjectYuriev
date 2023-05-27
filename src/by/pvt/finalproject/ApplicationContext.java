package by.pvt.finalproject;

import controller.AdminController;
import controller.ClientController;
import controller.UserController;
import repositories.AccountRepository;
import repositories.CurrencyRepository;
import repositories.OperationRepository;
import repositories.UserRepository;
import services.AccountService;
import services.AdminService;
import services.OperationService;
import services.UserService;
import services.impl.AccountServiceImpl;
import services.impl.AdminServiceImpl;
import services.impl.OperationServiceImpl;
import services.impl.UserServiceImpl;

public class ApplicationContext {
     AccountRepository accountRepository = new AccountRepository();
     UserRepository userRepository = new UserRepository();
     OperationRepository operationRepository = new OperationRepository();
     CurrencyRepository currencyRepository = new CurrencyRepository();
     AccountValidator accountValidator = new AccountValidator(accountRepository);

     UserService userService = new UserServiceImpl(userRepository, accountRepository);
     AccountService accountService = new AccountServiceImpl(accountRepository, userRepository, currencyRepository, accountValidator);
     OperationService operationService = new OperationServiceImpl(operationRepository, accountRepository, currencyRepository, userRepository);
     AdminService adminService = new AdminServiceImpl(accountRepository, operationRepository, userRepository, currencyRepository);

    private static ApplicationContext applicationContext;
    private UserController userController = new UserController(userService);;
    private ClientController clientController = new ClientController(accountService, operationService, userService, currencyRepository);
    private AdminController adminController = new AdminController(accountService, operationService, adminService, userService);

    public ApplicationContext() {
    }


    public static ApplicationContext getInstance() {
        if(applicationContext == null) {
            return new ApplicationContext();
        }
        return applicationContext;
    }

    public UserController getUserController() {
        if(userController == null) {
            return new UserController(userService);
        }
        return userController;
    }

    public ClientController getClientController() {
        if(clientController == null) {
            return new ClientController(accountService, operationService, userService, currencyRepository);
        }
        return clientController;
    }

    public AdminController getAdminController() {
        if(adminController == null) {
            return new AdminController(accountService, operationService, adminService, userService);
        }
        return adminController;
    }
}
