package com.company.spacecompany.screen.gas;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Gas;

@UiController("sc_Gas.browse")
@UiDescriptor("gas-browse.xml")
@LookupComponent("gasesTable")
public class GasBrowse extends StandardLookup<Gas> {
}