package car.manager.bl.exporters;

import car.manager.bl.models.Car;
import car.manager.bl.models.ElectricCar;
import car.manager.bl.models.GasCar;
import car.manager.bl.models.HybridCar;

import java.util.List;

public class CsvCarExporter implements ICarExporter {
    @Override
    public String Export(List<Car> cars) {
        StringBuilder result = new StringBuilder();
        for (Car car: cars) {

            result.append(car.type + ", ");
            result.append(car.brand + ", ");
            result.append(car.model + ", ");
            result.append(car.enginePower.toString() + ", ");

            if(car instanceof GasCar){
                GasCar gasCar = (GasCar)car;
                result.append(gasCar.engineDisplacement.toString() + ", ");
            } else if(car instanceof HybridCar){
                HybridCar hybridCar = (HybridCar)car;
                result.append(hybridCar.engineDisplacement.toString() + ", ");
                result.append(hybridCar.batteryCapacity.toString() + ", ");

            } else if(car instanceof ElectricCar){
                ElectricCar electricCar = (ElectricCar)car;
                result.append(electricCar.batteryCapacity.toString() + ", ");
            }

            result.append(car.price.toString());
            result.append(String.format("%n", ""));
        }

        return result.toString();
    }
}
