package com.company.spacecompany.screen.individual;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Individual;

@UiController("sc_Individual.browse")
@UiDescriptor("individual-browse.xml")
@LookupComponent("individualsTable")
public class IndividualBrowse extends StandardLookup<Individual> {
}