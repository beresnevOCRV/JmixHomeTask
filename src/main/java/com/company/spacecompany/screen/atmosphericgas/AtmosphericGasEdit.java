package com.company.spacecompany.screen.atmosphericgas;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.AtmosphericGas;

@UiController("sc_AtmosphericGas.edit")
@UiDescriptor("atmospheric-gas-edit.xml")
@EditedEntityContainer("atmosphericGasDc")
public class AtmosphericGasEdit extends StandardEditor<AtmosphericGas> {
}