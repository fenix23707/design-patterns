package by.vsu.kovzov;

import by.vsu.kovzov.dao.OperationDao;
import by.vsu.kovzov.dao.file.OperationDaoFileImpl;
import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.operations.CallOperation;
import by.vsu.kovzov.models.tariffs.impl.ATariff;
import by.vsu.kovzov.services.OperationService;
import by.vsu.kovzov.services.impl.OperationServiceImpl;

import java.util.Date;

import static by.vsu.kovzov.Constants.TARIFFS;

public class Main {

    private static OperationService operationService;

    static {
        OperationDao operationDao = new OperationDaoFileImpl("data/operations.data");
        operationService = new OperationServiceImpl(operationDao);
    }

    public static void main(String[] args) {
        Phone phone = new Phone("00375", "55", "1111111");
        Subscriber subscriber = new Subscriber(phone, TARIFFS.get(ATariff.class));
        CallOperation operation = new CallOperation(new CallInfo(subscriber, subscriber, new Date(), new Date(new Date().getTime() + 100 * 1000)));
        operationService.save(operation);
        System.out.println(operationService.findAll());
    }
}
