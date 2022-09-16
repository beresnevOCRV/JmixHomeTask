package com.company.spacecompany.screen.carrier;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Carrier;

@UiController("sc_Carrier.edit")
@UiDescriptor("carrier-edit.xml")
@EditedEntityContainer("carrierDc")
public class CarrierEdit extends StandardEditor<Carrier> {
}