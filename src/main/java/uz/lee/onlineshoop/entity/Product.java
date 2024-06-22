package uz.lee.onlineshoop.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Product {
    @Id
    @GeneratedValue
    private Long id;
}
