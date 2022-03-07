package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.OperationDao;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.operations.Operation;
import by.vsu.kovzov.services.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {
    private OperationDao operationDao;

    public OperationServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
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
        if (operation.getId() == null) {
            operation = operationDao.create(operation);
        } else {
        }
        return operation;
    }
}
