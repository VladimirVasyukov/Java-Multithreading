package com.epam.multithreading.model.factory;

import com.epam.multithreading.model.media.Wiretapping;

import java.util.ResourceBundle;
import java.util.function.Supplier;

@FunctionalInterface
public interface Factory<T extends Wiretapping> extends Supplier<T> {
    ResourceBundle CONFIGURATION_PARAMETERS = ResourceBundle.getBundle("configuration_parameters");

    T get();
}
