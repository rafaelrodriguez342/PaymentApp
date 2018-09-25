package com.studie.mercadolibre.models.retrofit;

public class PaymentMethodDTO {
    private final String id;
    private final String name;
    private final String thumbnail;

    PaymentMethodDTO() {
        id = null;
        name = null;
        thumbnail = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
