package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class uploadControl {

    String caption;
    @FXML
    private Button uploadButton;

    @FXML
    private TextField captionField;

    @FXML
    private Button confirmButton;

    @FXML
    void confirmPressed(ActionEvent event) {
        caption = captionField.getText();
        //Implementation for the photo class in progress
    }

    @FXML
    void uploadPressed(ActionEvent event) {
        //Investigating on how to upload the photo the user uploads.
    }

}
