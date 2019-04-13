package car.manager.bl.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
public class MeasureUnit {
    public Object value;
    public MeasureUnitType type;

    @Override
    public String toString() {
        if(value instanceof Double) {
            var symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator('.');

            var decimalFormat = new DecimalFormat ("0.#",symbols);
            return decimalFormat.format(value) + type;
        }

        return value + type.toString();
    }
}
