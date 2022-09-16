package com.company.spacecompany.screen.moon;

import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Moon;

@UiController("sc_Moon.browse")
@UiDescriptor("moon-browse.xml")
@LookupComponent("moonsTable")
public class MoonBrowse extends StandardLookup<Moon> {
}