package com.studie.mercadolibre.repositories.interfaces;

import com.studie.mercadolibre.models.PaymentMethod;
import com.studie.mercadolibre.repositories.network.NetworkRequestListener;

import java.util.List;

public interface PaymentMethodRepository {

    void getPaymentMethods(NetworkRequestListener<List<PaymentMethod>> responseListener);

}
