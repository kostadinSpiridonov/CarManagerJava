package car.manager.bl;

public class Provider {

    private static CarHolder carHolder;

    public static CarHolder getCarHolder(){
        if(carHolder == null){
            carHolder = new CarHolder();
        }

        return carHolder;
    }
}
