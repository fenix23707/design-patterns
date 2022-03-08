package by.vsu.services;

import by.vsu.models.Subscriber;
import by.vsu.models.operations.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> findAll();
    List<Operation> findAllBySubscriber(Subscriber subscriber);

    Operation save(Operation operation);
}
