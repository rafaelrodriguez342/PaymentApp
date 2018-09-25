package com.studie.mercadolibre.repositories.interfaces;

import com.studie.mercadolibre.models.PayerCost;
import com.studie.mercadolibre.repositories.network.NetworkRequestListener;

import java.util.List;

public interface DuesRecommendationRepository {

    void getRecommendedMessages(NetworkRequestListener<List<PayerCost>> responseListener, String paymentMethodId, String amount, String issuerId);
}
