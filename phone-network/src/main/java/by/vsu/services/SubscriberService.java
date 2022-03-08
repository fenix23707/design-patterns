package by.vsu.services;

import by.vsu.models.Phone;
import by.vsu.models.Subscriber;

import java.math.BigDecimal;
import java.util.List;

public interface SubscriberService {
    List<Subscriber> findAll();

    Subscriber findByPhone(Phone phone);

    Subscriber save(Subscriber subscriber);

    void updateBalance(Subscriber owner, BigDecimal balance);
}
