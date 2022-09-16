package com.company.spacecompany.screen.individual;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Individual;

@UiController("sc_Individual.edit")
@UiDescriptor("individual-edit.xml")
@EditedEntityContainer("individualDc")
public class IndividualEdit extends StandardEditor<Individual> {
}