package com.company.spacecompany.screen.spaceport;

import com.company.spacecompany.entity.Moon;
import com.company.spacecompany.entity.Planet;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Spaceport;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sc_Spaceport.edit")
@UiDescriptor("spaceport-edit.xml")
@EditedEntityContainer("spaceportDc")
public class SpaceportEdit extends StandardEditor<Spaceport> {

    @Autowired
    private EntityPicker<Planet> planetField;
    @Autowired
    private EntityPicker<Moon> moonField;
    @Autowired
    private InstanceContainer<Spaceport> spaceportDc;
    @Autowired
    private Notifications notifications;

    @Subscribe("moonField")
    public void onMoonFieldValueChange(HasValue.ValueChangeEvent<Moon> event) {
        if (event.getValue() != null) {
            planetField.setEditable(false);
        } else {
            planetField.setEditable(true);
        }
    }

    @Subscribe("planetField")
    public void onPlanetFieldValueChange(HasValue.ValueChangeEvent<Planet> event) {
        if (event.getValue() != null) {
            moonField.setEditable(false);
        } else {
            moonField.setEditable(true);
        }
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (spaceportDc.getItem().getPlanet() != null && spaceportDc.getItem().getMoon() != null) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption("Invalid params")
                    .withDescription("Can't select both planet and moon")
                    .show();

            event.preventCommit();
        }
    }

}