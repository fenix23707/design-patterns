package by.vsu;

import by.vsu.dao.OperationDao;
import by.vsu.dao.SubscriberDao;
import by.vsu.dao.file.SubscriberDaoFileImpl;
import by.vsu.dao.file.OperationDaoFileImpl;
import by.vsu.models.Phone;
import by.vsu.models.Subscriber;
import by.vsu.models.info.CallInfo;
import by.vsu.models.info.InternetInfo;
import by.vsu.models.info.SmsInfo;
import by.vsu.models.operations.CallOperation;
import by.vsu.models.operations.InternetOperation;
import by.vsu.models.operations.Operation;
import by.vsu.models.operations.SmsOperation;
import by.vsu.models.tariffs.Tariff;
import by.vsu.services.OperationService;
import by.vsu.services.SubscriberService;
import by.vsu.services.impl.OperationServiceImpl;
import by.vsu.services.impl.SubscriberServiceImpl;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static by.vsu.Constants.OUTER_OPERATOR_CODES;
import static by.vsu.Constants.TARIFFS;

public class Main {
    private final static String dataDir = "data/";

    private static List tariffs = Arrays.asList(TARIFFS.keySet().stream().toArray());

    private static OperationService operationService;

    private static SubscriberService subscriberService;

    private final static Random RANDOM = new Random();

    static {
        SubscriberDao subscriberDao = new SubscriberDaoFileImpl(dataDir + "subscribers.data");
        subscriberService = new SubscriberServiceImpl(subscriberDao);

        OperationDao operationDao = new OperationDaoFileImpl(dataDir + "operations.data");
        operationService = new OperationServiceImpl(operationDao, subscriberService);

    }

    public static void main(String[] args) {
        cleanData();
        initSubscribers();
        initOperations();

        printList(operationService.findAll());
        System.out.println();
        printList(subscriberService.findAll());
    }

    public static void initOperations() {
        List<Subscriber> subscribers = subscriberService.findAll();
        for (int i = 0; i < 5; i++) {
            int choose = RANDOM.nextInt(3);
            Operation operation;
            if (choose == 0) {
                operation = getRandomCallOperation(subscribers);
            } else if (choose == 1) {
                operation = getRandomSmsOperation(subscribers);
            } else {
                operation = getRandomInternetOperation(subscribers);
            }
            operationService.save(operation);
        }
    }

    private static Operation getRandomCallOperation(List<Subscriber> subscribers) {

        CallInfo info = new CallInfo(subscribers.get(0),
                subscribers.get(1 + RANDOM.nextInt(subscribers.size() - 1)),
                new Date(),
                new Date(System.currentTimeMillis() + 1000 * RANDOM.nextInt(60)));
        return new CallOperation(info);
    }

    private static Operation getRandomSmsOperation(List<Subscriber> subscribers) {
        SmsInfo info = new SmsInfo(subscribers.get(0),
                subscribers.get(1 + RANDOM.nextInt(subscribers.size() - 1)));
        return new SmsOperation(info);
    }

    private static Operation getRandomInternetOperation(List<Subscriber> subscribers) {
        InternetInfo info = new InternetInfo(subscribers.get(0),
                RANDOM.nextInt(100),
                new Date(),
                new Date(System.currentTimeMillis() + 1000 * RANDOM.nextInt(60)));
        return new InternetOperation(info);
    }

    public static void cleanData() {
        File file = new File(dataDir);
        Arrays.stream(file.listFiles()).forEach(f -> f.delete());
    }

    public static void initSubscribers() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
//            BigDecimal balance = BigDecimal.valueOf(random.nextInt(500));
            BigDecimal balance = BigDecimal.ZERO;
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

    private static void printList(List list) {
        list.forEach(System.out::println);
    }
}
