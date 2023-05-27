package services.impl;

import entities.Account;
import entities.Operation;
import entities.User;
import exceptions.ClientException;
import repositories.AccountRepository;
import repositories.CurrencyRepository;
import repositories.OperationRepository;
import repositories.UserRepository;
import services.OperationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OperationServiceImpl implements OperationService {
    private CurrencyRepository currencyRepository;
    private OperationRepository operationRepository;
    private AccountRepository accountRepository;
    private UserRepository userRepository;


    public OperationServiceImpl(OperationRepository operationRepository, AccountRepository accountRepository, CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;

    }

    @Override
    public List<Operation> operationsByUserAndAccount(Long id, Long acId, String dateFrom, String dateTo) {
        SimpleDateFormat dateTimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date localDateTime1 = null;
        Date localDateTime2 = null;
        try {
             localDateTime1 = dateTimeFormatter1.parse(dateFrom);
             localDateTime2 = dateTimeFormatter2.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Account account = accountRepository.findById(acId);
        List<Operation> operations = operationRepository.allOperations();
        List<Operation> operations1 = operations.stream().filter(operation -> operation.getClientIdToAccount().equals(account.getUserId())).collect(Collectors.toList());
        Date finalLocalDateTime = localDateTime1;
        Date finalLocalDateTime1 = localDateTime2;
        return operations1.stream().filter(operation -> operation.getLocalDate().after(finalLocalDateTime) && operation.getLocalDate().before(finalLocalDateTime1)).collect(Collectors.toList());
    }

    @Override
    public List<Operation> readAllOperations() {
        return operationRepository.allOperations();
    }

    @Override
    public Double calculateCommission() {
        List<Operation> operations = operationRepository.allOperations();
        List<Operation> operationList = operations.stream().filter(operation -> !operation.isCommissionPass()).collect(Collectors.toList());
        double commission = 0.0;
        for(Operation operation: operationList) {
            commission += operation.getCommission();
        }
        return commission;
    }

    @Override
    public List<Operation> getOperationByClient(Long clientId) {
        return operationRepository.getOperationsByClient(clientId);
    }

    @Override
    public Operation changeCurrency(Long fromAccountId, Long toAccountId, Double value, Long userId) {
        Account account1 = accountRepository.findById(fromAccountId);
        Account account2 = accountRepository.findById(toAccountId);
        User user1 = userRepository.findById(userId);
        User user2 = userRepository.findById(userId);
        Operation operation = new Operation();
        if (account1.getUserId().equals(account2.getUserId()) && account1.getCurrency().equals(account2.getCurrency())) {
            operation.setSum(account1.getValue() - value);
            operation.setClientIdFromAccount(fromAccountId);
            operation.setClientIdToAccount(toAccountId);
            operation.setClientFromName(user1.getName());
            operation.setClientFromSurname(user1.getSurname());
            operation.setClientToName(user2.getName());
            operation.setClientToSurname(user2.getSurname());
            operation.setClientFromAccount(account1.getAccountNumber());
            operation.setClientToAccount(account2.getAccountNumber());
            operation.setCurrency(account2.getCurrency());
            operation.setSum(account2.getValue() + value);
            operation.setId(operationRepository.getLastId() + 1);
            operation.setCommission(value * 0.001);
            operation.setLocalDate(new Date());
            operation.setCommissionPass(false);
        } else if (account1.getUserId().equals(account2.getUserId()) && !account1.getCurrency().equals(account2.getCurrency())) {
            operation.setSum(account1.getValue() - currencyRepository.convert(value, account1.getId(), account2.getId()));
            operation.setClientIdFromAccount(fromAccountId);
            operation.setClientIdToAccount(toAccountId);
            operation.setClientFromAccount(account1.getAccountNumber());
            operation.setClientToAccount(account2.getAccountNumber());
            operation.setClientFromName(user1.getName());
            operation.setClientFromSurname(user1.getSurname());
            operation.setClientToName(user2.getName());
            operation.setClientToSurname(user2.getSurname());
            operation.setCurrency(account2.getCurrency());
            operation.setSum(account2.getValue() + currencyRepository.convert(value, account1.getId(), account2.getId()));
            operation.setId(operationRepository.getLastId() + 1);
            operation.setCommission(value * 0.001);
            operation.setLocalDate(new Date());
            operation.setCommissionPass(true);
        }
        if (account1.getValue() - value > 0 && !account1.getCurrency().equals(account2.getCurrency())) {
            account1.setValue(account1.getValue() - value);
            account2.setValue(account2.getValue() +  currencyRepository.convert(value, account1.getId(), account2.getId()));
            accountRepository.saveChangesWithAccount();
        }
        else if(account1.getValue() - value > 0 && account1.getCurrency().equals(account2.getCurrency())) {
            account1.setValue(account1.getValue() - value);
            account2.setValue(account2.getValue() + value);
            accountRepository.saveChangesWithAccount();
        }
        operationRepository.addOperation(operation);
        return operation;
    }

    @Override
    public List<Operation> operationsByCommission() {
        List<Operation> operations = operationRepository.allOperations();
        return operations.stream().filter(operation -> !operation.isCommissionPass()).collect(Collectors.toList());
    }

    @Override
    public void passAllCommissions(Long accountId, Long userId) {
        List<Operation> operations = operationRepository.allOperations();
        User user = userRepository.findById(userId);
            Double comission = 0.0;
            for (Operation operation : operations) {
                comission = operation.getCommission();
            }
            Account account = accountRepository.findById(accountId);
            if (user.getId().equals(account.getUserId())) {
                account.setValue(account.getValue() + comission);
                accountRepository.saveChangesWithAccount();
                System.out.println(account);
            }
            else {
                throw new ClientException("Функция доступна только для администратора");
            }
    }
}
