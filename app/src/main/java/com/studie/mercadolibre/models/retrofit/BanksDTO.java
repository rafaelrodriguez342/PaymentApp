package com.studie.mercadolibre.models.retrofit;

import com.google.gson.annotations.SerializedName;

public class BanksDTO {
    private String id;
    private String name;
    @SerializedName("secure_thumbnail")
    private String secureThumbnail;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }
}
