package car.manager.views;

import car.manager.App;
import car.manager.bl.CarFactory;
import car.manager.bl.CarHolder;
import car.manager.bl.Provider;
import car.manager.bl.models.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static car.manager.bl.utils.StringUtils.empty;

public class CreateCar implements IPanel{
    public JPanel createCar;
    private JRadioButton electrictRadioButton;
    private JRadioButton gasRadioButton;
    private JRadioButton hybridRadioButton;
    private JTextField brandInput;
    private JTextField modelInput;
    private JTextField enginePowerInput;
    private JTextField priceInput;
    private JTextField batteryCapacityInput;
    private JTextField engineDisplacementInput;
    private JLabel batteryCapacityLabel;
    private JLabel engineDisplacementLabel;
    private JButton saveButton;
    private JLabel errorLabel;
    private JLabel batteryCapacityUnit;
    private JLabel engineDisplacementUnit;
    private JButton cancelButton;

    private CarHolder carHolder;
    private CarFactory carBuilder;

    CreateCar(){
        carHolder = Provider.getCarHolder();
        carBuilder = new CarFactory();
        init();
    }

    @Override
    public JPanel getPanel() {
        return createCar;
    }

    private void init(){
        electrictRadioButton.addActionListener((ActionEvent e) -> showFormByCarType(CarType.ELECTRIC_CAR));
        hybridRadioButton.addActionListener((ActionEvent e) -> showFormByCarType(CarType.HYBRID_CAR));
        gasRadioButton.addActionListener((ActionEvent e) -> showFormByCarType(CarType.GAS_CAR));
        saveButton.addActionListener((ActionEvent e) -> createCar());
        cancelButton.addActionListener((ActionEvent e) -> App.Navigate(new Home()));
    }

    private void createCar(){
        var carType = getSelectedCarType();

        if(!validate(carType)){
            return;
        }

        var carProperties = new ArrayList<String>();
        carProperties.add(carType.toString());
        carProperties.add(brandInput.getText());
        carProperties.add(modelInput.getText());
        carProperties.add(enginePowerInput.getText() + MeasureUnitType.KW);

        if(carType == CarType.HYBRID_CAR){
            carProperties.add(engineDisplacementInput.getText() + MeasureUnitType.L);
            carProperties.add(batteryCapacityInput.getText() + MeasureUnitType.Ah);
        } else if(carType == CarType.GAS_CAR){
            carProperties.add(engineDisplacementInput.getText() + MeasureUnitType.L);
        } else if(carType == CarType.ELECTRIC_CAR){
            carProperties.add(batteryCapacityInput.getText() + MeasureUnitType.Ah);
        }
        carProperties.add(priceInput.getText() + MeasureUnitType.euro);

        var car = carBuilder.build(carProperties);
        carHolder.add(car);

        App.Navigate(new Home());
    }

    private CarType getSelectedCarType(){
        if(hybridRadioButton.isSelected()){
            return CarType.HYBRID_CAR;
        } else if(gasRadioButton.isSelected()){
            return CarType.GAS_CAR;
        } else if(electrictRadioButton.isSelected()){
            return CarType.ELECTRIC_CAR;
        }

        return null;
    }

    private void showFormByCarType(CarType carType){
        switch(carType){
            case GAS_CAR:{
                batteryCapacityInput.setVisible(false);
                batteryCapacityLabel.setVisible(false);
                batteryCapacityUnit.setVisible(false);
                engineDisplacementInput.setVisible(true);
                engineDisplacementLabel.setVisible(true);
                engineDisplacementUnit.setVisible(true);
                break;
            }
            case HYBRID_CAR:{
                batteryCapacityInput.setVisible(true);
                batteryCapacityLabel.setVisible(true);
                batteryCapacityUnit.setVisible(true);
                engineDisplacementInput.setVisible(true);
                engineDisplacementLabel.setVisible(true);
                engineDisplacementUnit.setVisible(true);
                break;
            }
            case ELECTRIC_CAR:{
                batteryCapacityInput.setVisible(true);
                batteryCapacityLabel.setVisible(true);
                batteryCapacityUnit.setVisible(true);
                engineDisplacementInput.setVisible(false);
                engineDisplacementLabel.setVisible(false);
                engineDisplacementUnit.setVisible(false);
                break;
            }
        }
    }

    private boolean validate(CarType carType){
        var doubleRegex = "[0-9]{1,13}(\\.[0-9]*)?";

        if(empty(brandInput.getText())||
            empty(modelInput.getText())||
            empty(enginePowerInput.getText())||
            empty(priceInput.getText())||
            (carType != CarType.GAS_CAR && empty(batteryCapacityInput.getText()))||
            (carType != CarType.ELECTRIC_CAR && empty(engineDisplacementInput.getText()))){
            errorLabel.setText("Please fill all fields.");
            return false;
        }

        if (!priceInput.getText().matches(doubleRegex)){
            errorLabel.setText("Price should be a number.");
            return false;
        }
        if (!enginePowerInput.getText().matches(doubleRegex)){
            errorLabel.setText("Engine power should be a number.");
            return false;
        }
        if (carType != CarType.GAS_CAR && !batteryCapacityInput.getText().matches(doubleRegex)){
            errorLabel.setText("Battery capacity be a number.");
            return false;
        }
        if (carType != CarType.ELECTRIC_CAR && !engineDisplacementInput.getText().matches(doubleRegex)){
            errorLabel.setText("Engine displacement should be a number.");
            return false;
        }

        return true;
    }
}
