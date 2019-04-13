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

    public void Add(Car car){
        cars.add(car);
        SortBy((a, b) -> 0);
    }

    public void SortBy(Comparator<Car> comparator){
        cars.sort(comparator);
    }

    public String Export(ICarExporter exporter){
        return exporter.Export(cars);
    }

    public void Import(ICarImporter importer){
        cars = importer.Import();
    }
}