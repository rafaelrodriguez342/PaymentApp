package com.studie.mercadolibre.models;

import com.studie.mercadolibre.repositories.interfaces.PaymentOption;

public class PaymentMethod implements PaymentOption {
    private String id;
    private String name;
    private String image;

    public PaymentMethod(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return image;
    }
}
