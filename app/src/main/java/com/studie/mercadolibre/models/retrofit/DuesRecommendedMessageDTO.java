package com.studie.mercadolibre.models.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DuesRecommendedMessageDTO {

    @SerializedName("payer_costs")
    private
    List<PayerCostDTO> payerCosts;

    public List<PayerCostDTO> getPayerCosts() {
        return payerCosts;
    }
}
