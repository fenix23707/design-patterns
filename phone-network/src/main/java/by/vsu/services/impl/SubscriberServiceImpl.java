package by.vsu.services.impl;

import by.vsu.dao.SubscriberDao;
import by.vsu.models.Phone;
import by.vsu.models.Subscriber;
import by.vsu.services.SubscriberService;

import java.math.BigDecimal;
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

    @Override
    public void updateBalance(Subscriber subscriber, BigDecimal balance) {
        subscriber.setBalance(balance);
        subscriberDao.update(subscriber);
    }
}
