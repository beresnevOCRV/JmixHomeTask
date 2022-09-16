package com.company.spacecompany.screen.atmosphere;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Atmosphere;

@UiController("sc_Atmosphere.browse")
@UiDescriptor("atmosphere-browse.xml")
@LookupComponent("atmospheresTable")
public class AtmosphereBrowse extends StandardLookup<Atmosphere> {
}