package com.company.spacecompany.app;

import com.company.spacecompany.entity.Spaceport;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.SaveContext;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("sc_SpaceportEventListener")
public class SpaceportEventListener {

    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onSpaceportChangedBeforeCommit(EntityChangedEvent<Spaceport> event) {
        if (event.getType() != EntityChangedEvent.Type.DELETED
            && event.getChanges().isChanged("isDefault")) {

            Spaceport spaceport = dataManager.load(Spaceport.class)
                    .id(event.getEntityId())
                    .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE)
                            .add("planet")
                            .add("moon"))
                    .one();

            Boolean isDefaultOld = event.getChanges().getOldValue("isDefault");
            if (!Boolean.TRUE.equals(isDefaultOld)) {
                changeDefaultSpacePort(spaceport);
            }
        }
    }

    private void changeDefaultSpacePort(Spaceport spaceport) {
        List<Spaceport> defaultSpacePorts = dataManager.load(Spaceport.class)
                .query("select s from sc_Spaceport s where s.planet.id = :planetId and s.moon.id = :moonId" +
                        " and s.isDefault = :isDefault and s.id <> :id")
                .parameter("planetId", spaceport.getPlanet() != null ?
                        spaceport.getPlanet().getId() : null)
                .parameter("moonId", spaceport.getMoon() != null ?
                        spaceport.getMoon().getId() : null)
                .parameter("isDefault", true)
                .parameter("id", spaceport.getId())
                .list();

        if (defaultSpacePorts.size() == 0) {
            return;
        }

        defaultSpacePorts.forEach(sp -> sp.setIsDefault(false));
        dataManager.save(new SaveContext().saving(defaultSpacePorts));
    }

}