package car.manager.bl;

import car.manager.bl.models.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;

public class CarBuilder {

    public Car build(List<String> properties){
        try {
            var carType = properties.get(0);

            if (carType.equals(CarType.ELECTRIC_CAR.toString())) {
                return new ElectricCar(
                        properties.get(1).trim(),
                        properties.get(2).trim(),
                        parseMeasureUnit(properties.get(3)),
                        parseMeasureUnit(properties.get(4)),
                        parseMeasureUnit(properties.get(5))
                );
            } else if (carType.equals(CarType.GAS_CAR.toString())) {
                return new GasCar(
                        properties.get(1).trim(),
                        properties.get(2).trim(),
                        parseMeasureUnit(properties.get(3)),
                        parseMeasureUnit(properties.get(4)),
                        parseMeasureUnit(properties.get(5))
                );
            } else if (carType.equals(CarType.HYBRID_CAR.toString())) {
                return new HybridCar(
                        properties.get(1).trim(),
                        properties.get(2).trim(),
                        parseMeasureUnit(properties.get(3)),
                        parseMeasureUnit(properties.get(4)),
                        parseMeasureUnit(properties.get(5)),
                        parseMeasureUnit(properties.get(6))
                );
            }
        }
        catch(Exception e){
            throw new InvalidParameterException("The string is not correct csv format.");
        }

        throw new InvalidParameterException("The car type is not supported.");
    }

    private MeasureUnit parseMeasureUnit(String value){
        var measureUnit = new MeasureUnit();

        if(value.contains(MeasureUnitType.euro.toString())){
            measureUnit.type = MeasureUnitType.euro;
        }else if(value.contains(MeasureUnitType.Ah.toString())){
            measureUnit.type = MeasureUnitType.Ah;
        }else if(value.contains(MeasureUnitType.KW.toString())){
            measureUnit.type = MeasureUnitType.KW;
        }else if(value.contains(MeasureUnitType.L.toString())){
            measureUnit.type = MeasureUnitType.L;
        }

        String rawValue = value.replaceAll(measureUnit.type.toString(),"").trim();
        if(measureUnit.type == MeasureUnitType.euro){
            measureUnit.value = new BigDecimal(rawValue);
        }else{
            measureUnit.value = Double.parseDouble(rawValue);
        }

        return measureUnit;
    }
}
