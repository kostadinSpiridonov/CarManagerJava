package car.manager.bl.models;

public abstract class Car {
     public String brand;
     public String model;
     public MeasureUnit price;
     public MeasureUnit enginePower;
     public CarType type;

     public Car(
             String brand,
             String model,
             MeasureUnit price,
             MeasureUnit enginePower){
          this.brand = brand;
          this.model = model;
          this.price = price;
          this.enginePower = enginePower;
     }
}