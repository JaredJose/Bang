package Control;

import Model.Memory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class uploadControl {
    File imageSrc;
    LocalDate date;

    @FXML
    private Button uploadButton;

    @FXML
    private TextField captionField;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField dateField;

    @FXML
    void backToTimeline(ActionEvent event) {
        //Figure out how the project is going to start first before work on this
    }

    @FXML
    void confirmPressed(ActionEvent event) {
        date = LocalDate.now();
        Memory newMem = new Memory(imageSrc,date,captionField.getText());
    }

    @FXML
    void uploadPressed(ActionEvent event) {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

       imageSrc = fileChooser.showOpenDialog(stage);
    }



}
