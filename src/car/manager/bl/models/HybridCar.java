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
        this.type = CarType.HYBRID_CAR;
    }
}
