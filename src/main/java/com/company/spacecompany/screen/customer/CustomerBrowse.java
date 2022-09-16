package com.company.spacecompany.screen.customer;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Customer;

@UiController("sc_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}