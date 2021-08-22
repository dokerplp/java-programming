package com.company.memory;

import com.company.commandPattern.Remove;
import com.company.data.Product;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Keeps collection and registers
 */
public class Vault {

    private final List<Product> collection = new ArrayList<>();
    private ZonedDateTime lastInit = null;

    public synchronized ZonedDateTime getLastInit() {
        synchronized (collection) {
            return lastInit;
        }
    }

    public synchronized int getSize() {
        synchronized (collection) {
            return collection.size();
        }
    }

    public synchronized List<Product> getClone() {
        synchronized (collection) {
            return new ArrayList<>(collection);
        }
    }

    public synchronized double average() {
        synchronized (collection) {
            return collection.stream().mapToLong(Product::getPrice).average().isPresent() ? collection.stream().mapToLong(Product::getPrice).average().getAsDouble() : -1;
        }
    }

    public synchronized boolean higherPrice(long price) {
        synchronized (collection) {
            return collection.stream().anyMatch(product -> product.getPrice() > price);
        }
    }

    public synchronized long countByManufactureCost(float cost) {
        synchronized (collection) {
            return collection.stream().filter(product -> product.getManufactureCost() == cost).count();
        }
    }

    public synchronized long countLessThanManufactureCost(float cost) {
        synchronized (collection) {
            return collection.stream().filter(product -> product.getManufactureCost() < cost).count();
        }
    }

    public synchronized List<Product> prodById(int id) {
        synchronized (collection) {
            return collection.stream().filter(product -> product.getId() == id).collect(Collectors.toList());
        }
    }

    public synchronized boolean isEmpty() {
        synchronized (collection) {
            return collection.isEmpty();
        }
    }

    public synchronized void addOne(Product product) {
        synchronized (collection) {
            collection.add(product);
            lastInit = ZonedDateTime.now();
        }
    }

    public synchronized void addAll(List<Product> products) {
        synchronized (collection) {
            collection.addAll(products);
        }
    }

    public synchronized Remove removeById(int id, int user) {
        synchronized (collection) {
            if (collection.stream().anyMatch(product -> product.getId() == id)) {
                if (collection.stream().anyMatch(product -> product.getId() == id && product.getUserId() == user)) {
                    collection.removeIf(product -> product.getId() == id);
                    return Remove.SUCCESS;
                } else return Remove.WRONG_CLIENT_ID;
            } else return Remove.WRONG_PRODUCT_ID;
        }
    }

    public synchronized void removeAll(int id) {
        synchronized (collection) {
            collection.removeIf(product -> product.getUserId() == id);
        }
    }

    public synchronized void removeGreater(long price, int user) {
        synchronized (collection) {
            collection.removeIf(product -> product.getPrice() > price && product.getUserId() == user);
        }
    }

    public synchronized void shuffle() {
        synchronized (collection) {
            Collections.shuffle(collection);
        }
    }
}
