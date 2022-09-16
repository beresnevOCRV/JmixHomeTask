package com.company.spacecompany.screen.gas;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Gas;

@UiController("sc_Gas.edit")
@UiDescriptor("gas-edit.xml")
@EditedEntityContainer("gasDc")
public class GasEdit extends StandardEditor<Gas> {
}