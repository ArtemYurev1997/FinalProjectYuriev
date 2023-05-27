package repositories;

import entities.Account;
import entities.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperationRepository extends FileWorker{
    public static List<Operation> operations = new ArrayList<>();

    public static String FILE = "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/operations";

    public List<Operation> updateOperations() {
        Object object = deserializeObject(FILE);
        if(object instanceof List<?>) {
            operations = (List<Operation>) object;
        }
        return operations;
    }

    public List<Operation> allOperations()  {
        Object object = deserializeObject(FILE);
        List<Operation> operations = new ArrayList<>();
        if(object instanceof List<?>) {
            operations = (List<Operation>) object;
        }
        return operations;
    }

    public List<Operation> getOperationsByClient(Long clientId) {
        List<Operation> operationsByClient = operations.stream().filter(operation -> operation.getClientIdFromAccount().equals(clientId)).collect(Collectors.toList());
        if (operationsByClient.isEmpty()) {
            return new ArrayList<>();
        }
        return operationsByClient;
    }

    public void saveChangesWithAccount() {
        serializeObject(operations, FILE);
        updateOperations();
    }
    public Operation addOperation(Operation operation) {
        operations = allOperations();
        operations.add(operation);
        serializeObject(operations, FILE);
        return operation;
    }

    public Long getLastId() {
        operations = allOperations();
        if(operations.isEmpty()) {
            return 0L;
        }
        return operations.get(operations.size() - 1).getId();
    }

    public List<Operation> getOperationsByAccount(Long acId) {
        List<Operation> operationsByClient = operations.stream().filter(operation -> operation.getClientIdToAccount().equals(acId)).collect(Collectors.toList());
        if (operationsByClient.isEmpty()) {
            return new ArrayList<>();
        }
        return operationsByClient;
    }
}
