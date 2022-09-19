package com.company.spacecompany.security;

import com.company.spacecompany.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name="WaybillFullAccess", code="waybill-full-access")
public interface WaybillFullAccess {

    @JpqlRowLevelPolicy(entityClass = Waybill.class, where = "")
    void waybillFullAccess();
}
