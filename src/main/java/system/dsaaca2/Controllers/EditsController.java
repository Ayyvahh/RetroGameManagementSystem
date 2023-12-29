package system.dsaaca2.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;

import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.*;

public class EditsController implements Initializable {
    public static EditsController editsController = new EditsController();

    @FXML
    public TableView<GamesMachine> machineEditTable = new TableView<>();
    @FXML
    public TableColumn<GamesMachine, String> mName;
    @FXML
    public TableColumn<GamesMachine, String> mMan;
    @FXML
    public TableColumn<GamesMachine, String> mDesc;
    @FXML
    public TableColumn<GamesMachine, String> mType;
    @FXML
    public TableColumn<GamesMachine, String> mMedia;
    @FXML
    public TableColumn<GamesMachine, String> mImage;
    @FXML
    public TableColumn<GamesMachine, Integer> mYear;
    @FXML
    public TableColumn<GamesMachine, Double> mPrice;

    /*----UPDATE TEXT FIELDS-----*/
    public TextField updateMachineName = new TextField();
    public TextField updateMachineMan = new TextField();
    public TextField updateMachineDesc = new TextField();
    public TextField updateMachineType = new TextField();
    public TextField updateMachinePrice = new TextField();
    public TextField updateMachineYear = new TextField();
    public TextField updateMachineImage = new TextField();
    public TextField updateMachineMedia = new TextField();

    /*----------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EditsController.editsController = this;
        machineEditTable.getItems().addAll(allMachines);
        mName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mMan.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        mDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        mType.setCellValueFactory(new PropertyValueFactory<>("type"));
        mMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
        mImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        mYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        mPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public static EditsController getEditsController() {
        return editsController;
    }

    public void onMachineSelect() {
        GamesMachine selectedMachine = machineEditTable.getSelectionModel().getSelectedItem();

        if (selectedMachine != null) {
            // Update text fields with the values of the selected machine
            updateMachineName.setText(selectedMachine.getName());
            updateMachineMan.setText(selectedMachine.getManufacturer());
            updateMachineDesc.setText(selectedMachine.getDescription());
            updateMachineType.setText(selectedMachine.getType());
            updateMachinePrice.setText(String.valueOf(selectedMachine.getPrice()));
            updateMachineYear.setText(String.valueOf(selectedMachine.getYear()));
            updateMachineImage.setText(selectedMachine.getImage());
            updateMachineMedia.setText(selectedMachine.getMedia());
        }
    }

    public void applyMachineUpdate() {
        GamesMachine selectedMachine = machineEditTable.getSelectionModel().getSelectedItem();

        if (selectedMachine != null) {
            if (!updateMachineName.getText().isEmpty() && !updateMachineMan.getText().isEmpty() && !updateMachineDesc.getText().isEmpty() && !updateMachineType.getText().isEmpty() && !updateMachineMedia.getText().isEmpty() && !updateMachineImage.getText().isEmpty() && !updateMachinePrice.getText().isEmpty() && !updateMachineYear.getText().isEmpty()) {
                selectedMachine.setName(updateMachineName.getText());
                selectedMachine.setManufacturer(updateMachineMan.getText());
                selectedMachine.setDescription(updateMachineDesc.getText());
                selectedMachine.setType(updateMachineType.getText());
                selectedMachine.setMedia(updateMachineMedia.getText());
                selectedMachine.setImage(updateMachineImage.getText());
                try {
                    selectedMachine.setPrice(Double.parseDouble(updateMachinePrice.getText()));
                } catch (NumberFormatException e) {
                    Utilities.showWarningAlert("ERROR", "PLEASE USE NUMERICAL VALUES ONLY FOR PRICE FIELD.");
                    return;
                }
                try {
                    selectedMachine.setYear(Integer.parseInt(updateMachineYear.getText())); /* Parsing the string value input to convert to a numerical value */
                    if (!Utilities.intValidRange(selectedMachine.getYear(), 1900, 2024)) {
                        Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                        return;
                    }
                } catch (NumberFormatException e) {
                    Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                    return;
                }

                refreshAllViews();
                Utilities.showInformationAlert("SUCCESS", "UPDATE SUCCESSFUL\nMACHINE: ");
            } else
                Utilities.showWarningAlert("ERROR", "PLEASE DO NOT LEAVE ANY FIELDS EMPTY");
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A MACHINE TO UPDATE");
    }

    public void refreshAllViews() {
        gameAPI.portGameCombo.getItems().clear();
        gameAPI.portGameCombo.getItems().addAll(allGames);

        gameAPI.currentPortsView.getItems().clear();
        gameAPI.currentPortsView.getItems().addAll(allGamePorts);

        gameAPI.currentMachinesView.getItems().clear();
        gameAPI.currentMachinesView.getItems().addAll(allMachines);

        gameAPI.portMachineCombo.getItems().clear();
        gameAPI.portMachineCombo.getItems().addAll(allMachines);

        machineEditTable.getItems().clear();
        machineEditTable.getItems().addAll(allMachines);

        gameAPI.currentGamesView.getItems().clear();
        gameAPI.currentGamesView.getItems().addAll(allGames);
    }
}



