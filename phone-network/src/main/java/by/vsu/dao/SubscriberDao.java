package by.vsu.dao;

import by.vsu.models.Phone;
import by.vsu.models.Subscriber;

import java.util.List;

public interface SubscriberDao {
    List<Subscriber> findAll();

    Subscriber findByPhone(Phone phone);

    Subscriber create(Subscriber subscriber);

    Subscriber update(Subscriber subscriber);
}
