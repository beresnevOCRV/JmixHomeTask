package com.company.spacecompany.screen.spaceport;

import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Spaceport;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sc_Spaceport.browse")
@UiDescriptor("spaceport-browse.xml")
@LookupComponent("spaceportsTable")
public class SpaceportBrowse extends StandardLookup<Spaceport> {

    @Autowired
    private CollectionLoader<Spaceport> spaceportsDl;

    @Install(to = "spaceportsTable.edit", subject = "afterCommitHandler")
    private void spaceportsTableEditAfterCommitHandler(Spaceport spaceport) {
        spaceportsDl.load();
    }

    @Install(to = "spaceportsTable.create", subject = "afterCommitHandler")
    private void spaceportsTableCreateAfterCommitHandler(Spaceport spaceport) {
        spaceportsDl.load();
    }

}