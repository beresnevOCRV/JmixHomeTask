package com.company.spacecompany.security;

import com.company.spacecompany.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name="WaybillCurrentUserAccess", code="waybill-current-user-access")
public interface WaybillCurrentUserAccess {

    @JpqlRowLevelPolicy(entityClass = Waybill.class, where = "{E}.creator.id = :current_user_id")
    void waybillForCurrentUser();
}
