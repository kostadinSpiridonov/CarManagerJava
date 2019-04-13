package car.manager.bl.models;

public class GasCar  extends Car {
    public  MeasureUnit engineDisplacement;

    public GasCar(
            String brand,
            String model,
            MeasureUnit enginePower,
            MeasureUnit engineDisplacement,
            MeasureUnit price){
        super(brand, model, price, enginePower);
        this.engineDisplacement = engineDisplacement;
        this.type = CarType.GAS_CAR;
    }
}
