package by.vsu.kovzov.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    String category;
    String name;
    double price;
}
