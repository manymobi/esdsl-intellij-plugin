package com.manymobi.jetbrains.esdsl;

import com.intellij.lang.Language;

public class EsdslLanguage extends Language {
    public static final EsdslLanguage INSTANCE = new EsdslLanguage();

    private EsdslLanguage() {
        super("Esdsl");
    }
}
