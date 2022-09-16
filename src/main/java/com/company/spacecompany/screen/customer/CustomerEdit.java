package com.company.spacecompany.screen.customer;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Customer;

@UiController("sc_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
public class CustomerEdit extends StandardEditor<Customer> {
}