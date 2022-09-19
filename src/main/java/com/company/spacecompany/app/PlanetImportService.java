package com.company.spacecompany.app;

import com.company.spacecompany.entity.Planet;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Component("sc_PlanetImportService")
public class PlanetImportService {

    @Autowired
    private DataManager dataManager;

    public void importPlanets(List<String[]> strings) {
        List<Planet> planets = new ArrayList<>(strings.size());

        for (String[] s: strings) {
            Planet planet = loadPlanet(s);
            Planet planetToSave = (planet != null) ? planet : dataManager.create(Planet.class);

            planetToSave.setName(planetToSave.getName() != null ? planetToSave.getName() : s[0]);
            planetToSave.setMass(Double.valueOf(s[2]));
            planetToSave.setSemiMajorAxis(Double.valueOf(s[3]));
            planetToSave.setOrbitalPeriod(Double.valueOf(s[4]));
            planetToSave.setRotationPeriod(Double.valueOf(s[6]));
            planetToSave.setRings(!s[10].equals("no"));

            planets.add(planetToSave);
        }
        dataManager.save(planets.toArray());
    }

    @Nullable
    public Planet loadPlanet(String[] strings) {
        return  dataManager.load(Planet.class)
                .query("select p from sc_Planet p where p.name = :name")
                .parameter("name", strings[0])
                .optional().orElse(null);
    }

 }