package car.manager.bl.models;

public class ElectricCar extends Car{
    private MeasureUnit batteryCapacity;

    public ElectricCar(
            String brand,
            String model,
            MeasureUnit enginePower,
            MeasureUnit batteryCapacity,
            MeasureUnit price){
        super(brand, model, price, enginePower);
        this.batteryCapacity = batteryCapacity;
        this.setType(CarType.ELECTRIC_CAR);
    }

    public MeasureUnit getBatteryCapacity(){
        return batteryCapacity;
    }

    public void setBatteryCapacity(MeasureUnit batteryCapacity){
        this.batteryCapacity = batteryCapacity;
    }
}
