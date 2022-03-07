package by.vsu.kovzov.dao.file;

import by.vsu.kovzov.dao.OperationDao;
import by.vsu.kovzov.models.operations.Operation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDaoFileImpl implements OperationDao {
    private String fileName;

    public OperationDaoFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Operation> findAll() {
        return read();
    }

    @Override
    public Operation create(Operation operation) {
        operation.setId(System.currentTimeMillis());

        List<Operation> operations = read();
        operations.add(operation);
        write(operations);

        return operation;
    }

    private List<Operation> read() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Operation>) stream.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void write(List<Operation> operations) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(operations);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
