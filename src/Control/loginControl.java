package Control;

import Model.User;
import View.TimelineUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginControl {

    static String username, password;
    User user;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    void loginPressed(ActionEvent event) throws Exception {
        //Ensure fields are not empty with the if statements
        if(!usernameField.getText().equals(""))
            username = usernameField.getText();
        if(!passwordField.getText().equals(""))
            password = passwordField.getText();

        user = new User(username,password);

        //Check if user exists in the database and the password is correct
        //if that is true, then in the driver class, we will create a new TimelineUI object to start the program.

        //This code will just open Timeline for testing
        TimelineUI mainProgram = new TimelineUI();
        mainProgram.startProgram();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    private boolean userConfirm()
    {
        boolean result = false;
        //Add code for testing if we have the user in the database
        //return true if we do, else false

        return result;
    }

}