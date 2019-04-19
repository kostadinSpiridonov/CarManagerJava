package car.manager.bl.models;

public abstract class Car {
     private String brand;
     private String model;
     private MeasureUnit price;
     private MeasureUnit enginePower;
     private CarType type;

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

     public String getBrand(){
          return brand;
     }

     public void setBrand(String brand){
          this.brand = brand;
     }

     public String getModel(){
          return model;
     }

     public void setModel(String model){
          this.model = model;
     }

     public MeasureUnit getPrice(){
          return price;
     }

     public void setPrice(MeasureUnit price){
          this.price = price;
     }

     public MeasureUnit getEnginePower(){
          return enginePower;
     }

     public void setEnginePower(MeasureUnit enginePower){
          this.enginePower = enginePower;
     }

     public CarType getType(){
          return type;
     }

     public void setType(CarType type){
          this.type = type;
     }
}