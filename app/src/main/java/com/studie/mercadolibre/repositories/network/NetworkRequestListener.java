package com.studie.mercadolibre.repositories.network;

public interface NetworkRequestListener<T> {

    void onSuccessNetworkRequest(T response);

   void onFailureRequest(Throwable t);
}
