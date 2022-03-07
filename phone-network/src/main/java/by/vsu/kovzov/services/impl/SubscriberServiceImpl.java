package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.SubscriberDao;
import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.services.SubscriberService;

import java.util.List;

public class SubscriberServiceImpl implements SubscriberService {
    private SubscriberDao subscriberDao;

    public SubscriberServiceImpl(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }

    @Override
    public List<Subscriber> findAll() {
        return subscriberDao.findAll();
    }

    @Override
    public Subscriber findByPhone(Phone phone) {
        return subscriberDao.findByPhone(phone);
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        Subscriber oldValue = findByPhone(subscriber.getPhone());
        if (oldValue == null) {
            subscriber = subscriberDao.create(subscriber);
        } else {
            oldValue.setBalance(subscriber.getBalance());
            oldValue.setTariff(subscriber.getTariff());
            subscriber = subscriberDao.update(oldValue);
        }
        return subscriber;
    }
}
