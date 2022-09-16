package com.company.spacecompany.screen.atmosphericgas;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.AtmosphericGas;

@UiController("sc_AtmosphericGas.browse")
@UiDescriptor("atmospheric-gas-browse.xml")
@LookupComponent("atmosphericGasesTable")
public class AtmosphericGasBrowse extends StandardLookup<AtmosphericGas> {
}