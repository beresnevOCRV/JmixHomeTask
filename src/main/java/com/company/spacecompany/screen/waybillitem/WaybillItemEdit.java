package com.company.spacecompany.screen.waybillitem;

import com.company.spacecompany.app.WaybillItemService;
import com.company.spacecompany.entity.Waybill;
import io.jmix.core.DataManager;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Form;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.WaybillItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@UiController("sc_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
public class WaybillItemEdit extends StandardEditor<WaybillItem> {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private InstanceContainer<WaybillItem> waybillItemDc;
    @Autowired
    private WaybillItemService waybillItemService;
    @Autowired
    private Form form;
    private Integer formNumber = 0;
    @Autowired
    private TextField<BigDecimal> chargeField;

    @Subscribe
    public void onInitEntity(InitEntityEvent<WaybillItem> event) {
        WaybillItem waybillItem = event.getEntity();

        event.getEntity().setNumber(waybillItemService.getNumber(waybillItem));
    }

    @Subscribe("itemUp")
    public void onItemUpClick(Button.ClickEvent event) {
        WaybillItem item = waybillItemDc.getItem();
        Waybill waybill = item.getWaybill();

        List<WaybillItem> sortedItems = waybill.getItem().stream()
                .sorted(Comparator.comparing(WaybillItem::getNumber))
                .collect(Collectors.toList());

        if (formNumber == 0) {
            formNumber = item.getNumber();
        }

        int number = item.getNumber() - 1;
        if (number >= 1) {
            waybillItemDc.setItem(sortedItems.get(number - 1));

            if (formNumber != waybillItemDc.getItem().getNumber()) {
                form.setEditable(false);
            } else {
                form.setEditable(true);
            }
        }
    }

    @Subscribe("itemDown")
    public void onItemDownClick(Button.ClickEvent event) {
        WaybillItem item = waybillItemDc.getItem();
        Waybill waybill = item.getWaybill();

        List<WaybillItem> sortedItems = waybill.getItem().stream()
                .sorted(Comparator.comparing(WaybillItem::getNumber))
                .collect(Collectors.toList());

        if (formNumber == 0) {
            formNumber = item.getNumber();
        }

        int number = item.getNumber();
        if (waybill.getItem().size() > number) {
            waybillItemDc.setItem(sortedItems.get(number));
        }

        if (formNumber != waybillItemDc.getItem().getNumber()) {
            form.setEditable(false);
        } else {
            form.setEditable(true);
        }
    }

    @Subscribe("weightField")
    public void onWeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(calcCharge());
    }

    @Subscribe("dimWidthField")
    public void onDimWidthFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(calcCharge());
    }

    @Subscribe("dimHeightField")
    public void onDimHeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(calcCharge());
    }

    @Subscribe("dimLengthField")
    public void onDimLengthFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(calcCharge());
    }


    private BigDecimal calcCharge() {
        waybillItemService.calcChargeField(waybillItemDc.getItem());
        return waybillItemDc.getItem().getCharge();
    }

}