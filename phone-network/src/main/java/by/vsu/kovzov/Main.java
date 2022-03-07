package by.vsu.kovzov;

import by.vsu.kovzov.dao.OperationDao;
import by.vsu.kovzov.dao.SubscriberDao;
import by.vsu.kovzov.dao.file.OperationDaoFileImpl;
import by.vsu.kovzov.dao.file.SubscriberDaoFileImpl;
import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.operations.CallOperation;
import by.vsu.kovzov.models.operations.Operation;
import by.vsu.kovzov.models.tariffs.Tariff;
import by.vsu.kovzov.models.tariffs.impl.ATariff;
import by.vsu.kovzov.services.OperationService;
import by.vsu.kovzov.services.SubscriberService;
import by.vsu.kovzov.services.impl.OperationServiceImpl;
import by.vsu.kovzov.services.impl.SubscriberServiceImpl;

import java.math.BigDecimal;
import java.util.*;

import static by.vsu.kovzov.Constants.OUTER_OPERATOR_CODES;
import static by.vsu.kovzov.Constants.TARIFFS;

public class Main {
    private static List<Class<? extends Tariff>> tariffs = Arrays.asList(ATariff.class);

    private static OperationService operationService;

    private static SubscriberService subscriberService;

    static {
        SubscriberDao subscriberDao = new SubscriberDaoFileImpl("data/subscribers.data");
        subscriberService = new SubscriberServiceImpl(subscriberDao);

        OperationDao operationDao = new OperationDaoFileImpl("data/operations.data");
        operationService = new OperationServiceImpl(operationDao, subscriberService);

    }

    public static void main(String[] args) {
        initSubscribers();
        initOperations();

        System.out.println(operationService.findAll());
        System.out.println(subscriberService.findAll());
    }

    public static void initOperations() {
        List<Subscriber> subscribers = subscriberService.findAll();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            CallInfo info = new CallInfo(subscribers.get(0),
                    subscribers.get(1 + random.nextInt(subscribers.size() - 1)),
                    new Date(),
                    new Date(System.currentTimeMillis() + 1000 * random.nextInt(60)));
            Operation operation = new CallOperation(info);
            operationService.save(operation);
        }
    }

    public static void initSubscribers() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            BigDecimal balance = BigDecimal.valueOf(random.nextInt(500));
            Subscriber subscriber = new Subscriber(getRandomPhone(), balance, randomTariff());
            subscriberService.save(subscriber);
        }
    }

    private static Phone getRandomPhone() {
        Random random = new Random();

        String countryCode = "0037" + (random.nextInt(4) == 3 ? "1" : "5");

        List<String> codes = OUTER_OPERATOR_CODES;
        String operationCode = codes.get(random.nextInt(codes.size()));

        String number = "";
        for (int i = 0; i < 7; i++) {
            number += random.nextInt(10);
        }

        return new Phone(countryCode, operationCode, number);
    }

    private static Tariff randomTariff() {
        return TARIFFS.get(tariffs.get(new Random().nextInt(tariffs.size())));
    }
}
