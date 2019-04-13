package car.manager.bl.importers;

import car.manager.bl.CarBuilder;
import car.manager.bl.models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvCarImporter implements ICarImporter {

    private String csv;
    private CarBuilder carBuilder;

    public CsvCarImporter(){
        carBuilder = new CarBuilder();
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    @Override
    public List<Car> Import() {
        var result = new ArrayList<Car>();
        for (String line: csv.split("\\r?\\n")) {
            var properties = Arrays.asList(line.split(","));
            result.add(carBuilder.Build(properties));
        }

        return result;
    }
}
