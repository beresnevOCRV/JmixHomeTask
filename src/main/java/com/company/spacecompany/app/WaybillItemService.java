package com.company.spacecompany.app;

import com.company.spacecompany.entity.Discounts;
import com.company.spacecompany.entity.Waybill;
import com.company.spacecompany.entity.WaybillItem;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component("sc_WaybillItemService")
public class WaybillItemService {

    @Autowired
    private DataManager dataManager;

    public int getNumber(WaybillItem waybillItem) {
        List<WaybillItem> items =  waybillItem.getWaybill().getItem();

        return items.size() > 0 ? items.size() + 1 : 1;
    }

    public void calcChargeField(WaybillItem waybillItem){
        double k = 0.0;

        double height = waybillItem.getDim().getHeight() == null ? 0: waybillItem.getDim().getHeight();
        double width = waybillItem.getDim().getWidth() == null ? 0: waybillItem.getDim().getWidth();
        double length = waybillItem.getDim().getLength() == null ? 0: waybillItem.getDim().getLength();

        if (height == 0 && width == 0 && length == 0) {
            k = 10.0;
        }

        BigDecimal charge = BigDecimal.valueOf(((k + height + width + length)/100) * waybillItem.getWeight());
        waybillItem.setCharge(charge);
    }

    public void calcTotalWeight(Waybill waybill) {
        Double totalWeight = waybill.getItem().stream()
                .map(item -> item.getWeight())
                .filter(Objects::nonNull)
                .reduce(Double.valueOf(0), Double::sum);

        waybill.setTotalWeight(totalWeight);
    }

    public void calcTotalCharge(Waybill waybill) {
        BigDecimal totalCharge = waybill.getItem().stream()
                .map(item -> item.getCharge())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Discounts discount = dataManager.load(Discounts.class)
                .query("select d from sc_Discounts d where d.grade = :grade")
                .parameter("grade", waybill.getShipper() != null ?
                        waybill.getShipper().getGrade() : null)
                .optional().orElse(null);

        if (discount != null && !discount.getValue().equals(BigDecimal.ZERO)) {
            totalCharge = totalCharge.subtract(
                    (discount.getValue().divide(
                            BigDecimal.valueOf(100))).multiply(
                                    totalCharge));
        }

        waybill.setTotalCharge(totalCharge);
    }

    public void calcTotals(Waybill waybill) {
        calcTotalWeight(waybill);
        calcTotalCharge(waybill);
    }
}