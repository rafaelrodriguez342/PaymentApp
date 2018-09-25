package com.studie.mercadolibre.di.components;

import com.studie.mercadolibre.ApplicationClass;
import com.studie.mercadolibre.di.modules.CoreModule;
import com.studie.mercadolibre.di.modules.UIControllersModule;
import com.studie.mercadolibre.di.modules.ViewModelsModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Dagger application component.
 */
@Singleton
@Component(modules = {CoreModule.class, AndroidInjectionModule.class, UIControllersModule.class, AndroidSupportInjectionModule.class, ViewModelsModule.class})
public interface AppComponent extends AndroidInjector<ApplicationClass> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<ApplicationClass> {
    }
}
