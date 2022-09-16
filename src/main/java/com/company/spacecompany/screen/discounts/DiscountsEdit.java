package com.company.spacecompany.screen.discounts;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Discounts;

@UiController("sc_Discounts.edit")
@UiDescriptor("discounts-edit.xml")
@EditedEntityContainer("discountsDc")
public class DiscountsEdit extends StandardEditor<Discounts> {
}