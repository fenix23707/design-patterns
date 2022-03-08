package by.vsu.services.impl;

import by.vsu.dao.OperationDao;
import by.vsu.models.Subscriber;
import by.vsu.models.operations.Operation;
import by.vsu.services.OperationService;
import by.vsu.services.SubscriberService;

import java.math.BigDecimal;
import java.util.List;

public class OperationServiceImpl implements OperationService {
    private OperationDao operationDao;

    private SubscriberService subscriberService;

    public OperationServiceImpl(OperationDao operationDao, SubscriberService subscriberService) {
        this.operationDao = operationDao;
        this.subscriberService = subscriberService;
    }

    @Override
    public List<Operation> findAll() {
        return operationDao.findAll();
    }

    @Override
    public List<Operation> findAllBySubscriber(Subscriber subscriber) {
        return null;
    }

    @Override
    public Operation save(Operation operation) {
        BigDecimal balance = calculateBalance(operation);
        subscriberService.updateBalance(operation.getOwner(), balance);
        operation = operationDao.create(operation);
        return operation;
    }

    private BigDecimal calculateBalance(Operation operation) {
        Subscriber subscriber = operation.getOwner();
        BigDecimal balance = subscriber.getBalance();
        balance = balance.subtract(operation.getPrice());
        return balance;
    }
}
