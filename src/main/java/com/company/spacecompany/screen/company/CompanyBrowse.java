package com.company.spacecompany.screen.company;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Company;

@UiController("sc_Company.browse")
@UiDescriptor("company-browse.xml")
@LookupComponent("companiesTable")
public class CompanyBrowse extends StandardLookup<Company> {
}