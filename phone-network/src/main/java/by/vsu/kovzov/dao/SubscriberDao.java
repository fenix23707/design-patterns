package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;

import java.util.List;

public interface SubscriberDao {
    List<Subscriber> findAll();

    Subscriber findByPhone(Phone phone);

    Subscriber create(Subscriber subscriber);

    Subscriber update(Subscriber subscriber);
}
