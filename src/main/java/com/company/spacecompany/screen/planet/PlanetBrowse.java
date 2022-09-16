package com.company.spacecompany.screen.planet;

import com.company.spacecompany.app.PlanetImportService;
import io.jmix.ui.component.FileStorageUploadField;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.spacecompany.entity.Planet;
import io.jmix.ui.upload.TemporaryStorage;
import liquibase.repackaged.com.opencsv.CSVReader;
import liquibase.repackaged.com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@UiController("sc_Planet.browse")
@UiDescriptor("planet-browse.xml")
@LookupComponent("planetsTable")
public class PlanetBrowse extends StandardLookup<Planet> {

    @Autowired
    private PlanetImportService planetImportService;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private FileStorageUploadField importPlanets;
    @Autowired
    private CollectionLoader<Planet> planetsDl;

    @Subscribe("importPlanets")
    public void onImportPlanetsFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        UUID id = importPlanets.getFileId();
        File file = temporaryStorage.getFile(id);
        if (file != null) {
            try {
                CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()));
                List<String[]> strings = reader.readAll();

                strings.remove(0);
                planetImportService.importPlanets(strings);

                planetsDl.load();

                temporaryStorage.deleteFile(id);
            } catch (IOException | CsvException e) {
                throw new RuntimeException(e);
            }
        }
    }
}