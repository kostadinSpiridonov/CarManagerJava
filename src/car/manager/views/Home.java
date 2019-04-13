package car.manager.views;

import car.manager.App;
import car.manager.bl.CarHolder;
import car.manager.bl.Provider;
import car.manager.bl.exporters.CsvCarExporter;
import car.manager.bl.importers.CsvCarImporter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
        carHolder = Provider.GetCarHolder();
        csvExporter = new CsvCarExporter();
        csvImporter = new CsvCarImporter();

        Init();
        Display();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void Init(){
        createCarButton.addActionListener((e) -> App.Navigate(new CreateCar()));
        importButton.addActionListener((e) -> Import());
        exportButton.addActionListener((e) -> Export());
        saveButton.addActionListener((e) -> Save());
        resetButton.addActionListener((e) -> Display());

        sortBy.addActionListener ((e) ->  {
            Sort();
            Display();
        });

    }

    private void Sort(){
        var sort = sortBy.getSelectedItem();
        if(sort == "Brand"){
            carHolder.SortBy(Comparator.comparing(o -> o.brand));

        }else if(sort == "Price"){
            carHolder.SortBy(Comparator.comparing(o -> (BigDecimal)o.price.value));
        }
    }

    private void Display(){
        showTextPane.setText(carHolder.Export(csvExporter));
    }

    private void Import(){
        try {
            var fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(panel.getComponent(0));
            var csv = Files.readString(Paths.get(fileChooser.getSelectedFile().getPath()));
            Save(csv);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Export(){
        var fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("output.csv"));
        fileChooser.showSaveDialog(panel.getComponent(0));
        var csv = carHolder.Export(csvExporter);

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

    private void Save(){
        Save(showTextPane.getText());
    }

    private void Save(String csv){
        try {
            csvImporter.setCsv(csv);
            carHolder.Import(csvImporter);

            Sort();
            Display();
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
