package services;

import entities.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> operationsByUserAndAccount(Long id, Long acId, String dateFrom, String dateTo);

    List<Operation> readAllOperations();

    Double calculateCommission();

    List<Operation> getOperationByClient(Long clientId);

    Operation changeCurrency(Long fromAccountId, Long toAccountId, Double value , Long userId);

    List<Operation> operationsByCommission();

    void passAllCommissions(Long accountId, Long userId);
}
