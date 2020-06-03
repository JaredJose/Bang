package Control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import Model.memoryDatabaseMethods;

import static javafx.application.Application.launch;

public class _mainUIControl extends Application {

    private static Stage primaryStage;
    private static Pane mainLayout;

    public static void main(String[] args)
    {
        launch(args);
        for(File f:memoryDatabaseMethods.deleteAfter)
        {
        	if(f.delete())
        		System.out.print("deleted");
        }
    }

    public static void startProgram() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(_mainUIControl.class.getResource("/View/LoginUI.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Login");
        startProgram();
    }
}
