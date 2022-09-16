package com.company.spacecompany.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity
@Table(name = "SC_MOON", indexes = {
        @Index(name = "IDX_SC_MOON_PLANET", columnList = "PLANET_ID"),
        @Index(name = "IDX_SC_MOON_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "sc_Moon")
public class Moon extends AstronomicalBody {

    @JoinColumn(name = "PLANET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @JoinColumn(name = "ATMOSPHERE_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
}