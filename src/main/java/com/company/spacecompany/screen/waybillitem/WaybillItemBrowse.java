package com.company.spacecompany.screen.waybillitem;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.WaybillItem;

@UiController("sc_WaybillItem.browse")
@UiDescriptor("waybill-item-browse.xml")
@LookupComponent("waybillItemsTable")
public class WaybillItemBrowse extends StandardLookup<WaybillItem> {
}