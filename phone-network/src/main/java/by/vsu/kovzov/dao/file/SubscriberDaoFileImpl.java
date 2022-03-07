package by.vsu.kovzov.dao.file;

import by.vsu.kovzov.dao.SubscriberDao;
import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDaoFileImpl implements SubscriberDao {
    private final String FILE_PATH;

    public SubscriberDaoFileImpl(String filePath) {
        this.FILE_PATH = filePath;
    }

    @Override
    public List<Subscriber> findAll() {
        return read();
    }

    @Override
    public Subscriber findByPhone(Phone phone) {
        return read().stream()
                .parallel()
                .filter(subscriber -> subscriber.getPhone().equals(phone))
                .findAny()
                .orElse(null);
    }

    @Override
    public Subscriber create(Subscriber subscriber) {
        List<Subscriber> subscribers = read();
        subscribers.add(subscriber);
        write(subscribers);
        return subscriber;
    }

    @Override
    public Subscriber update(Subscriber subscriber) {
        List<Subscriber> subscribers = read();

        Subscriber oldSub = subscribers.stream()
                .filter(s -> s.getPhone().equals(subscriber.getPhone()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Subscriber: " + subscriber + " doesn't exist"));
        oldSub.setPhone(subscriber.getPhone());
        oldSub.setBalance(subscriber.getBalance());
        oldSub.setTariff(subscriber.getTariff());
        write(subscribers);
        return subscriber;
    }

    private List<Subscriber> read() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Subscriber>) stream.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void write(List<Subscriber> subscribers) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            stream.writeObject(subscribers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
