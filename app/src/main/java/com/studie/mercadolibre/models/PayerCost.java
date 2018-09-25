package com.studie.mercadolibre.models;

import com.studie.mercadolibre.repositories.interfaces.PaymentOption;

public class PayerCost implements PaymentOption {
    private String recommendedMessage;
    private String installments;

    public PayerCost(String recommendedMessage, String installments) {
        this.recommendedMessage = recommendedMessage;
        this.installments = installments;
    }

    public String getInstallments() {
        return installments;
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getName() {
        return recommendedMessage;
    }
}
