package com.company.spacecompany.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.UUID;

@JmixEntity
@Table(name = "SC_ATMOSPHERIC_GAS", indexes = {
        @Index(name = "IDX_SC_ATMOSPHERIC_GAS_GAS", columnList = "GAS_ID"),
        @Index(name = "IDX_ATMOSPHERIC_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "sc_AtmosphericGas")
public class AtmosphericGas {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "GAS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gas gas;

    @Digits(integer = 2, fraction = 2)
    @Column(name = "VOLUME")
    private Double volume;

    @JoinColumn(name = "ATMOSPHERE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}