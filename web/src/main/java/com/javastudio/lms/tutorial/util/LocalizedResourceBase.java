package com.javastudio.lms.tutorial.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class LocalizedResourceBase {

    private final String messages = "com/javastudio/lms/tutorial/web/i18n/messages";
    private final String labels = "com/javastudio/lms/tutorial/web/i18n/labels";
    private final String validations = "com/javastudio/lms/tutorial/web/i18n/validations";

    protected String getMessage(Locale locale, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(messages, locale);
        return getString(resourceBundle, key);
    }

    protected String getLabel(Locale locale, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(labels, locale);
        return getString(resourceBundle, key);
    }

    protected String getValidation(Locale locale, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(validations, locale);
        return getString(resourceBundle, key);
    }

    protected String getString(ResourceBundle resourceBundle, String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return String.format("???%s???", key);
        }
    }

}
