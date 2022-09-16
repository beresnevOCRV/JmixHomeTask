package com.company.spacecompany.screen.moon;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Moon;

@UiController("sc_Moon.edit")
@UiDescriptor("moon-edit.xml")
@EditedEntityContainer("moonDc")
public class MoonEdit extends StandardEditor<Moon> {
}