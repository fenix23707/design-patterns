package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.operations.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> findAllBySubscriber(Subscriber subscriber);

    Operation save(Operation operation);
}
