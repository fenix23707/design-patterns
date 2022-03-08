package by.vsu.dao;


import by.vsu.models.operations.Operation;

import java.util.List;

public interface OperationDao {
    List<Operation> findAll();
    Operation create(Operation operation);
}
