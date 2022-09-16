package com.company.spacecompany.screen.carrier;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Carrier;

@UiController("sc_Carrier.browse")
@UiDescriptor("carrier-browse.xml")
@LookupComponent("carriersTable")
public class CarrierBrowse extends StandardLookup<Carrier> {

}