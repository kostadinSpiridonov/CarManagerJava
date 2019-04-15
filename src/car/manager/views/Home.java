package car.manager.views;

import car.manager.App;
import car.manager.bl.CarHolder;
import car.manager.bl.Provider;
import car.manager.bl.exporters.CsvCarExporter;
import car.manager.bl.importers.CsvCarImporter;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Comparator;

public class Home implements IPanel{
    public JPanel panel;

    private JButton importButton;
    private JButton exportButton;
    private JButton createCarButton;
    private JTextPane showTextPane;
    private JComboBox sortBy;
    private JButton saveButton;
    private JButton resetButton;

    private CarHolder carHolder;
    private CsvCarExporter csvExporter;
    private CsvCarImporter csvImporter;

    public Home(){
        carHolder = Provider.getCarHolder();
        csvExporter = new CsvCarExporter();
        csvImporter = new CsvCarImporter();

        init();
        display();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void init(){
        createCarButton.addActionListener((e) -> App.Navigate(new CreateCar()));
        importButton.addActionListener((e) -> importData());
        exportButton.addActionListener((e) -> exportData());
        saveButton.addActionListener((e) -> save());
        resetButton.addActionListener((e) -> display());

        sortBy.addActionListener ((e) ->  {
            sort();
            display();
        });

    }

    private void sort(){
        var sort = sortBy.getSelectedItem();
        if(sort == "Brand"){
            carHolder.sortBy(Comparator.comparing(o -> o.brand));

        }else if(sort == "Price"){
            carHolder.sortBy(Comparator.comparing(o -> (BigDecimal)o.price.value));
        }
    }

    private void display(){
        showTextPane.setText(carHolder.exportData(csvExporter));
    }

    private void importData(){
        try {
            var fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(panel.getComponent(0));
            var csv = Files.readString(Paths.get(fileChooser.getSelectedFile().getPath()));
            save(csv);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportData(){
        var fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("output.csv"));
        fileChooser.showSaveDialog(panel.getComponent(0));
        var csv = carHolder.exportData(csvExporter);

        try {
            Files.writeString(Paths.get(fileChooser.getSelectedFile().getPath()),csv);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void save(){
        save(showTextPane.getText());
    }

    private void save(String csv){
        try {
            csvImporter.setCsv(csv);
            carHolder.importData(csvImporter);

            sort();
            display();
        } catch(InvalidParameterException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
