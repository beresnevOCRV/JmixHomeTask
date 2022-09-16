package com.company.spacecompany.screen.atmosphere;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Atmosphere;

@UiController("sc_Atmosphere.edit")
@UiDescriptor("atmosphere-edit.xml")
@EditedEntityContainer("atmosphereDc")
public class AtmosphereEdit extends StandardEditor<Atmosphere> {
}