package Control;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void loginPressed(ActionEvent event) {
        //Ensure fields are not empty with the if statements
        if(!usernameField.getText().equals(""))
            username = usernameField.getText();
        if(!passwordField.getText().equals(""))
            password = passwordField.getText();

        user = new User(username,password);

        //Check if user exists in the database and the password is correct
        //if that is true, then in the driver class, we will create a new TimelineUI object to start the program.
    }

    private boolean userConfirm()
    {
        boolean result = false;
        //Add code for testing if we have the user in the database
        //return true if we do, else false

        return result;
    }

}