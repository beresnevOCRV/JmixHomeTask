package com.company.spacecompany.app;

import com.company.spacecompany.entity.Waybill;
import com.company.spacecompany.entity.WaybillItem;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component("sc_WaybillItemListener")
public class WaybillItemListener {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private WaybillItemService waybillItemService;

    @EventListener
    public void onWaybillItemListener(@NotNull EntityChangedEvent<WaybillItem> event) {
        boolean chargeChanged = event.getChanges().isChanged("charge");
        boolean weightChanged = event.getChanges().isChanged("weight");

        WaybillItem waybillItem = dataManager.load(event.getEntityId()).one();
        Waybill waybill = dataManager.load(Waybill.class)
                .query("select w from sc_Waybill w where w.id = :id")
                .parameter("id", waybillItem.getWaybill().getId())
                .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE)
                        .add("item"))
                .one();

        if (weightChanged) {
            waybillItemService.calcTotalWeight(waybill);
        }

        if (chargeChanged) {
            waybillItemService.calcTotalCharge(waybill);
        }

        if (waybill != null) {
            dataManager.save(waybill);
        }
    }
    
}