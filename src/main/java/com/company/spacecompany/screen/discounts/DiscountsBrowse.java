package com.company.spacecompany.screen.discounts;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Discounts;

@UiController("sc_Discounts.browse")
@UiDescriptor("discounts-browse.xml")
@LookupComponent("discountsesTable")
public class DiscountsBrowse extends StandardLookup<Discounts> {
}