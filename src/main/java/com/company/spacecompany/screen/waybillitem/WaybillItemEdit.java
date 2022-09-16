package com.company.spacecompany.screen.waybillitem;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.WaybillItem;

@UiController("sc_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
public class WaybillItemEdit extends StandardEditor<WaybillItem> {

}