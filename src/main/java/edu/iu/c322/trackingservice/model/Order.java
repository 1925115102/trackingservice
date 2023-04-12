package edu.iu.c322.trackingservice.model;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;


public record Order(int id,
                    int customerId,
                    float total,
                    Address shippingAddress,
                    List<Item> items,
                    Payment payment) {
}