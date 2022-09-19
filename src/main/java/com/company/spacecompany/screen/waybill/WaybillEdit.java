package com.company.spacecompany.screen.waybill;

import com.company.spacecompany.app.WaybillItemService;
import com.company.spacecompany.entity.*;
import com.company.spacecompany.screen.carrier.CarrierBrowse;
import com.company.spacecompany.screen.company.CompanyBrowse;
import com.company.spacecompany.screen.individual.IndividualBrowse;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.Metadata;
import io.jmix.ui.RemoveOperation;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.TableItems;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@UiController("sc_Waybill.edit")
@UiDescriptor("waybill-edit.xml")
@EditedEntityContainer("waybillDc")
public class WaybillEdit extends StandardEditor<Waybill> {

    @Autowired
    private RadioButtonGroup shipperChoose;
    @Autowired
    private RadioButtonGroup consigneeChoose;
    @Autowired
    private CollectionContainer<Individual> individualsDc;
    @Autowired
    private CollectionContainer<Company> companiesDc;
    @Autowired
    private EntityComboBox shipperField;
    @Autowired
    private EntityPicker consigneeField;
    @Autowired
    private Metadata metadata;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private ComboBox departureSpaceObject;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private EntityPicker<Spaceport> departurePortField;
    @Autowired
    private EntityPicker<Spaceport> destinationPortField;
    @Autowired
    private FetchPlans fetchPlans;
    @Autowired
    private CollectionContainer<Carrier> carriersDc;
    @Autowired
    private EntityPicker carrierField;
    @Autowired
    private ComboBox destinationSpaceObject;
    @Autowired
    private Table<WaybillItem> itemsTable;
    @Autowired
    private InstanceContainer<Waybill> waybillDc;
    @Autowired
    private WaybillItemService waybillItemService;
    @Autowired
    private TextField<BigDecimal> totalChargeField;
    @Autowired
    private TextField<Double> totalWeightField;
    @Autowired
    private CollectionPropertyContainer<WaybillItem> itemsDc;

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> list = new ArrayList<>();
        list.add("Company");
        list.add("Individual");

        shipperChoose.setOptionsList(list);
        consigneeChoose.setOptionsList(list);

        List<Planet> planetsList = dataManager.load(Planet.class)
                .query("select p from sc_Planet p")
                .list();

        List<Moon> moonsList = dataManager.load(Moon.class)
                .query("select m from sc_Moon m")
                .list();

        int size = moonsList.size() + planetsList.size();
        List<String> spaceObjects = new ArrayList<>(size);

        planetsList.forEach(planet -> spaceObjects.add(planet.getName()));
        moonsList.forEach(moon -> spaceObjects.add(moon.getName()));
        departureSpaceObject.setOptionsList(spaceObjects);
        destinationSpaceObject.setOptionsList(spaceObjects);
    }

    @Subscribe("consigneeChoose")
    public void onConsigneeChooseValueChange(HasValue.ValueChangeEvent event) {
        String value = (String) event.getValue();

        if ("Individual".equals(value)) {
            consigneeField.setMetaClass(metadata.getClass(Individual.class));
        } else if ("Company".equals(value)) {
            consigneeField.setMetaClass(metadata.getClass(Company.class));
        }
    }

    @Subscribe("shipperChoose")
    public void onShipperChooseValueChange(HasValue.ValueChangeEvent event) {
        String value = (String) event.getValue();

        if ("Individual".equals(value)) {
            shipperField.setOptionsContainer(individualsDc);
        } else if ("Company".equals(value)) {
            shipperField.setOptionsContainer(companiesDc);
        }
    }

    @Subscribe("consigneeField.entityLookup")
    public void onConsigneeFieldEntityLookup(Action.ActionPerformedEvent event) {
        if (Objects.equals(consigneeField.getMetaClass(),
                metadata.getClass(Company.class))) {
            screenBuilders.lookup(consigneeField)
                    .withScreenClass(CompanyBrowse.class)
                    .build()
                    .show();
        } else if (Objects.equals(consigneeField.getMetaClass(),
                metadata.getClass(Individual.class))) {
            screenBuilders.lookup(consigneeField)
                    .withScreenClass(IndividualBrowse.class)
                    .build()
                    .show();
        }
    }

    @Subscribe("departureSpaceObject")
    public void onDepartureSpaceObjectValueChange(HasValue.ValueChangeEvent event) {
        String spaceObjectName = (String) event.getValue();
        Spaceport spaceport = obtainDefaultSpaceport(spaceObjectName);

        if (spaceport != null) {
            departurePortField.setValue(spaceport);
        }
    }

    @Subscribe("destinationSpaceObject")
    public void onDestinationSpaceObjectValueChange(HasValue.ValueChangeEvent event) {
        String spaceObjectName = (String) event.getValue();
        Spaceport spaceport = obtainDefaultSpaceport(spaceObjectName);

        if (spaceport != null) {
            destinationPortField.setValue(spaceport);
        }
    }

    private Spaceport obtainDefaultSpaceport(String spaceObjectName) {
        Spaceport spaceport = null;

        spaceport = dataManager.load(Spaceport.class)
                .query("select s from sc_Spaceport s where s.planet.name = :planetName" +
                        " and s.isDefault = :isDefault")
                .parameter("planetName",
                        spaceObjectName)
                .parameter("isDefault", true)
                .optional().orElse(null);

        if  (spaceport == null) {
            spaceport = dataManager.load(Spaceport.class)
                    .query("select s from sc_Spaceport s where s.moon.name = :moonName" +
                            " and s.isDefault = :isDefault")
                    .parameter("moonName",
                            spaceObjectName)
                    .parameter("isDefault", true)
                    .optional().orElse(null);
        }
        return spaceport;
    }

    @Subscribe("carrierField.entityLookup")
    public void onCarrierFieldEntityLookup(Action.ActionPerformedEvent event) {
        if (destinationPortField.getValue() == null
            || departurePortField.getValue() == null) {
        } else {
            Collection<Carrier> carriers = dataManager.load(Carrier.class)
                    .query("select distinct c from sc_Carrier c join c.ports p " +
                            "where p.name in (:portsName1, :portsName2)")
                    .parameter("portsName1", destinationPortField.getValue().getName())
                    .parameter("portsName2", departurePortField.getValue().getName())
                    .fetchPlan(fpb ->
                            fpb.addFetchPlan(FetchPlan.BASE).add("ports"))
                    .list();

            CarrierBrowse carrierScreen = (CarrierBrowse) screenBuilders.lookup(Carrier.class, this)
                    .withField(carrierField)
                    .build()
                    .show();
        }
    }

    @Install(to = "itemsTable.remove", subject = "afterActionPerformedHandler")
    private void itemsTableRemoveAfterActionPerformedHandler(RemoveOperation.AfterActionPerformedEvent<WaybillItem> afterActionPerformedEvent) {
        List<WaybillItem> items = afterActionPerformedEvent.getItems();

        Collection<WaybillItem> waybillItems = itemsTable.getItems().getItems();

        List<WaybillItem> newItems = waybillItems.stream()
                .filter(f -> f.getNumber() > items.get(0).getNumber())
                .collect(Collectors.toList());

        newItems.forEach(item -> item.setNumber(item.getNumber() - 1));

        Waybill waybill = items.get(0).getWaybill();

        waybillItemService.calcTotals(waybill);
        totalChargeField.setValue(waybill.getTotalCharge());
        totalWeightField.setValue(waybill.getTotalWeight());
    }

    @Install(to = "itemsTable.edit", subject = "afterCommitHandler")
    private void itemsTableEditAfterCommitHandler(WaybillItem waybillItem) {
        Waybill waybill = waybillItem.getWaybill();
        waybillItemService.calcTotals(waybill);

        totalChargeField.setValue(waybill.getTotalCharge());
        totalWeightField.setValue(waybill.getTotalWeight());
    }

    @Install(to = "itemsTable.create", subject = "afterCommitHandler")
    private void itemsTableCreateAfterCommitHandler(WaybillItem waybillItem) {
        Waybill waybill = waybillItem.getWaybill();
        waybillItemService.calcTotals(waybill);

        totalChargeField.setValue(waybill.getTotalCharge());
        totalWeightField.setValue(waybill.getTotalWeight());
    }
}