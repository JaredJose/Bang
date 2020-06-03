package Control;

import Model.Memory;
import Model.User;
import Model.loginDatabaseMethods;
import Model.memoryDatabaseMethods;
import View.TimelineUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class loginControl {

    public static String username;
	public static String password;
    User user;
    static ArrayList<Memory> MemoryList = new ArrayList<>();


    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label credentialLabel;

    @FXML
    void loginPressed(ActionEvent event) throws Exception {

        Stage stage = (Stage) loginButton.getScene().getWindow();

        //Ensure fields are not empty with the if statements
        if(!usernameField.getText().equals(""))
            username = usernameField.getText();
        if(!passwordField.getText().equals(""))
            password = passwordField.getText();

        System.out.println("\n\n\n\nhereherhehrer\n\n\n\n\n");
        loginDatabaseMethods db = new loginDatabaseMethods();
        int uID = db.getUserID(username,password); //getUserID() returns the userId associated with login credentials
        //uID = 5; //For Now since db methods still need to be implemented ID is 5 just to test
       
       
        System.out.println("UserID got");
        
        //This is to check if user exists in database
        if(uID == -1) {
            //If they are not in the database UI will say invalid user
            credentialLabel.setText("Invalid User");
            credentialLabel.setStyle("-fx-text-fill: #ff162c");
        } else {
            //If they are a valid user we will start the program

            //First, Build MemoryList off of uID;
            memoryDatabaseMethods datab = new memoryDatabaseMethods(uID);
            MemoryList = datab.queryDB();

            //Then, I will pass the built MemoryList to TimelineUI via constructor
            TimelineUI mainProgram = new TimelineUI(MemoryList);

           //This executes the code that builds the timelineUI
            mainProgram.startProgram();

            stage.close();
        }


    }

    private boolean userConfirm()
    {
        boolean result = false;
        //Add code for testing if we have the user in the database
        //return true if we do, else false

        return result;
    }


}