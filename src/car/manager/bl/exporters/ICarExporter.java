package car.manager.bl.exporters;

import car.manager.bl.models.Car;

import java.util.List;

public interface ICarExporter{
    String exportData(List<Car> cars);
}
