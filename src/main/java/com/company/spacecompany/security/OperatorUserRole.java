package com.company.spacecompany.security;

import com.company.spacecompany.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "OperatorUser", code = "operator-user", scope = "UI")
public interface OperatorUserRole {

    @MenuPolicy(menuIds = {"sc_Planet.browse", "sc_Moon.browse", "sc_Atmosphere.browse", "sc_AtmosphericGas.browse", "sc_Gas.browse", "sc_Customer.browse", "sc_Individual.browse", "sc_Company.browse", "sc_Discounts.browse", "sc_Spaceport.browse", "sc_Carrier.browse", "sc_Waybill.browse"})
    @ScreenPolicy(screenIds = {"sc_Planet.browse", "sc_Moon.browse", "sc_Atmosphere.browse", "sc_AtmosphericGas.browse", "sc_Gas.browse", "sc_Customer.browse", "sc_Individual.browse", "sc_Company.browse", "sc_Discounts.browse", "sc_Spaceport.browse", "sc_Carrier.browse", "sc_Waybill.browse", "sc_Atmosphere.edit", "sc_AtmosphericGas.edit", "sc_Carrier.edit", "sc_Company.edit", "sc_Customer.edit", "sc_Discounts.edit", "sc_Gas.edit", "sc_Individual.edit", "sc_Moon.edit", "sc_Planet.edit", "sc_Spaceport.edit", "sc_Waybill.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = AstronomicalBody.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AstronomicalBody.class, actions = EntityPolicyAction.READ)
    void astronomicalBody();

    @EntityAttributePolicy(entityClass = Atmosphere.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Atmosphere.class, actions = EntityPolicyAction.READ)
    void atmosphere();

    @EntityAttributePolicy(entityClass = AtmosphericGas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AtmosphericGas.class, actions = EntityPolicyAction.READ)
    void atmosphericGas();

    @EntityAttributePolicy(entityClass = Gas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Gas.class, actions = EntityPolicyAction.READ)
    void gas();

    @EntityAttributePolicy(entityClass = Moon.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Moon.class, actions = EntityPolicyAction.READ)
    void moon();

    @EntityAttributePolicy(entityClass = Planet.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Planet.class, actions = EntityPolicyAction.READ)
    void planet();

    @EntityAttributePolicy(entityClass = Carrier.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Carrier.class, actions = EntityPolicyAction.ALL)
    void carrier();

    @EntityAttributePolicy(entityClass = Company.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Company.class, actions = EntityPolicyAction.ALL)
    void company();

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.ALL)
    void customer();

    @EntityAttributePolicy(entityClass = Discounts.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Discounts.class, actions = EntityPolicyAction.ALL)
    void discounts();

    @EntityAttributePolicy(entityClass = Individual.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Individual.class, actions = EntityPolicyAction.ALL)
    void individual();

    @EntityAttributePolicy(entityClass = Spaceport.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Spaceport.class, actions = EntityPolicyAction.ALL)
    void spaceport();

    @EntityAttributePolicy(entityClass = WaybillItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = WaybillItem.class, actions = EntityPolicyAction.ALL)
    void waybillItem();

    @EntityAttributePolicy(entityClass = Waybill.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Waybill.class, actions = EntityPolicyAction.ALL)
    void waybill();
}