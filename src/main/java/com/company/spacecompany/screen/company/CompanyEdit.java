package com.company.spacecompany.screen.company;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Company;

@UiController("sc_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
public class CompanyEdit extends StandardEditor<Company> {
}