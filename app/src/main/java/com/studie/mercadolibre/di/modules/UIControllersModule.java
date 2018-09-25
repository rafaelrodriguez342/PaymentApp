package com.studie.mercadolibre.di.modules;

import com.studie.mercadolibre.ui.activities.MainActivity;
import com.studie.mercadolibre.ui.fragments.BankSelectionFragment;
import com.studie.mercadolibre.ui.fragments.DuesSelectionFragment;
import com.studie.mercadolibre.ui.fragments.PaymentAmountFragment;
import com.studie.mercadolibre.ui.fragments.PaymentMethodSelectionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Module to declare activities to be injected.
 */
@Module
public abstract class UIControllersModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract BankSelectionFragment bindBankFragment();

    @ContributesAndroidInjector
    abstract DuesSelectionFragment bindDuesFragment();

    @ContributesAndroidInjector
    abstract PaymentAmountFragment bindAmountFragment();

    @ContributesAndroidInjector
    abstract PaymentMethodSelectionFragment bindPaymentMethodFragment();
}
