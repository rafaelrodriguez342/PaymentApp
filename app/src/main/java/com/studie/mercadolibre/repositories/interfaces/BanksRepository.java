package com.studie.mercadolibre.repositories.interfaces;

import com.studie.mercadolibre.models.Bank;
import com.studie.mercadolibre.repositories.network.NetworkRequestListener;

import java.util.List;

public interface BanksRepository {

    void getBanks(NetworkRequestListener<List<Bank>> responseListener, String paymentMethodId);
}
