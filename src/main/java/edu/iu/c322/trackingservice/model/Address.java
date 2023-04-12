package edu.iu.c322.trackingservice.model;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

import java.util.Objects;

public record Address(int id,
                      String state,
                      String city,
                      int postalCode) {
}