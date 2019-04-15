package car.manager.bl;

import car.manager.bl.exporters.ICarExporter;
import car.manager.bl.importers.ICarImporter;
import car.manager.bl.models.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarHolder {
    private List<Car> cars;

    public CarHolder()    {
        cars = new ArrayList();
    }

    public void add(Car car){
        cars.add(car);
        sortBy((a, b) -> 0);
    }

    public void sortBy(Comparator<Car> comparator){
        cars.sort(comparator);
    }

    public String exportData(ICarExporter exporter){
        return exporter.exportData(cars);
    }

    public void importData(ICarImporter importer){
        cars = importer.importData();
    }
}