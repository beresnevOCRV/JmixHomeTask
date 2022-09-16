package com.company.spacecompany.screen.waybill;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Waybill;

@UiController("sc_Waybill.browse")
@UiDescriptor("waybill-browse.xml")
@LookupComponent("waybillsTable")
public class WaybillBrowse extends StandardLookup<Waybill> {
}