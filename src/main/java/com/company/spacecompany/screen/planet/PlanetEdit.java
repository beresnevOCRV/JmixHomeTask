package com.company.spacecompany.screen.planet;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Planet;

@UiController("sc_Planet.edit")
@UiDescriptor("planet-edit.xml")
@EditedEntityContainer("planetDc")
public class PlanetEdit extends StandardEditor<Planet> {
}