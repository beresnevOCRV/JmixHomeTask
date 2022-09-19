package com.company.spacecompany.security.specific;

import io.jmix.core.accesscontext.SpecificOperationAccessContext;

public class EnablePlanetImport extends SpecificOperationAccessContext {

    public static final String NAME = "enablePlanetImport";

    public EnablePlanetImport() {
        super(NAME);
    }
}
