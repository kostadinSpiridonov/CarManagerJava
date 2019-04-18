package car.manager.bl.models;

public class HybridCar extends Car{
    public MeasureUnit batteryCapacity;
    public MeasureUnit engineDisplacement;

    public HybridCar(
            String brand,
            String model,
            MeasureUnit enginePower,
            MeasureUnit engineDisplacement,
            MeasureUnit batteryCapacity,
            MeasureUnit price){
        super(brand, model, price, enginePower);
        this.engineDisplacement = engineDisplacement;
        this.batteryCapacity = batteryCapacity;
        this.setType(CarType.HYBRID_CAR);
    }

    public MeasureUnit getBatteryCapacity(){
        return batteryCapacity;
    }

    public void setBatteryCapacity(MeasureUnit batteryCapacity){
        this.batteryCapacity = batteryCapacity;
    }

    public MeasureUnit getEngineDisplacement(){
        return engineDisplacement;
    }

    public void setEngineDisplacement(MeasureUnit measureUnit){
        this.engineDisplacement = engineDisplacement;
    }
}
