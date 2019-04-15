package car.manager.bl.importers;

import car.manager.bl.models.Car;

import java.util.List;

public interface ICarImporter{
    List<Car> importData();
}