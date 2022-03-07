package by.vsu.kovzov.dao;


import by.vsu.kovzov.models.operations.Operation;

import java.util.List;

public interface OperationDao {
    List<Operation> findAll();
    Operation create(Operation operation);
}
