package com.company.spacecompany.security;

import com.company.spacecompany.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "StandartUser", code = "standart-user", scope = "UI")
public interface StandartUserRole {

    @MenuPolicy(menuIds = {"sc_Planet.browse", "sc_Moon.browse", "sc_Atmosphere.browse", "sc_Gas.browse", "sc_AtmosphericGas.browse"})
    @ScreenPolicy(screenIds = {"sc_Planet.browse", "sc_Moon.browse", "sc_Atmosphere.browse", "sc_Gas.browse", "sc_AtmosphericGas.browse", "sc_Atmosphere.edit", "sc_AtmosphericGas.edit", "sc_Gas.edit", "sc_Moon.edit"})
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
}