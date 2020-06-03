package Control;

import Model.Memory;
import Model.memoryDatabaseMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class uploadControl {
    File imageSrc;
    LocalDate date;
    String caption;

    @FXML
    private Button uploadButton;

    @FXML
    private TextField captionField;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField yearField;

    @FXML
    private Label validDate;

    @FXML
    private TextField monthField;

    @FXML
    private TextField dayField;

    @FXML
    void backToTimeline(ActionEvent event) {
        //Figure out how the project is going to start first before work on this
    }

    @FXML
    void confirmPressed(ActionEvent event) {
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        int day = Integer.parseInt(dayField.getText());
        date = LocalDate.of(year,month,day);
        caption = captionField.getText();
        Memory newMem = new Memory(imageSrc,date,captionField.getText());
        
        memoryDatabaseMethods.uploadMemory(newMem);
        
        //Ethan's database method that lets me add a memory to a user.
        Driver.setIsUploadAdded(true);
    }

    @FXML
    void uploadPressed(ActionEvent event) {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        imageSrc = fileChooser.showOpenDialog(stage);
    }



}
