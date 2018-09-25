package com.studie.mercadolibre.models.retrofit;

import com.google.gson.annotations.SerializedName;

public class PayerCostDTO {

    @SerializedName("recommended_message")
    private String recommendedMessage;

    private String installments;

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public String getInstallments() {
        return installments;
    }
}
