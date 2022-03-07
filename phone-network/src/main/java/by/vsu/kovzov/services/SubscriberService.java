package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;

import java.util.List;

public interface SubscriberService {
    List<Subscriber> findAll();

    Subscriber findByPhone(Phone phone);

    Subscriber save(Subscriber subscriber);
}
