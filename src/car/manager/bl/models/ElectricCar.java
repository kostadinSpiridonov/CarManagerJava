package car.manager.bl.models;

public class ElectricCar extends Car{
    public MeasureUnit batteryCapacity;

    public ElectricCar(
            String brand,
            String model,
            MeasureUnit enginePower,
            MeasureUnit batteryCapacity,
            MeasureUnit price){
        super(brand, model, price, enginePower);
        this.batteryCapacity = batteryCapacity;
        this.type = CarType.ELECTRIC_CAR;
    }
}
