package View;

import Model.Memory;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class TimelineUI extends Application {

    ArrayList<Memory> memories = new ArrayList<>();
    String caption;
    String date;
    File imageSrc;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        memories = Control.Driver.getList();
        primaryStage.setTitle("Timeline");


        HBox hbox = new HBox(20);
        hbox.getStyleClass().add("hbox");

        Button backBtn = new Button("Back to Home");
        hbox.getChildren().add(backBtn);

        /*
        for(Memory m : memories)
        {
            caption = m.getCaption();
            date = m.getTime().toString();
            imageSrc = m.getPhoto();
            Vbox memory = createMemory(m);
            hbox.setMargin(memory,new Insets(200,50,400,50));
            hbox.getChildren().add(memory);
        }
         */

        for(int i = 0; i < 20; i++) {
            VBox memory = createDummyMem();
            HBox.setMargin(memory, new Insets(200, 50, 400, 50));
            hbox.getChildren().add(memory);
        }

        ScrollPane layout = new ScrollPane();
        layout.setPrefSize(1200, 800);
        layout.setContent(hbox);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("/View/TimelineUI.css");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public VBox createMemory(Memory memory)
    {
        VBox vBox = new VBox();
        Label cap = new Label(memory.getCaption());
        Label date = new Label(memory.getTime().toString());
        ImageView image = new ImageView(memory.getPhoto().getPath()); //Don't exactly know how file is going to work, so i just put imageSrc.getName();

        vBox.setStyle("-fx-border-color: #78909C");
        vBox.setPrefSize(250,400);
        VBox.setMargin(cap,new Insets(20,20,20,20));
        VBox.setMargin(date,new Insets(20,20,20,20));
        VBox.setMargin(image,new Insets(20,20,20,20));

        vBox.getChildren().addAll(image,cap,date);
        vBox.getStyleClass().add("stack-pane");

        return vBox;
    }

    public VBox createDummyMem()
    {
        VBox vBox = new VBox(20);
        Label cap = new Label("BRUH!!!!!!!");
        Label date = new Label("2020-5-27");
        Image image = new Image("/View/bruh.jpeg");
        ImageView imageview = new ImageView(image); //Don't exactly know how file is going to work, so i just put imageSrc.toString();
        imageview.setFitHeight(200.0);
        imageview.setFitWidth(200.0);

        vBox.setStyle("-fx-border-color: #78909C");
        vBox.setPrefSize(250,400);
        VBox.setMargin(cap,new Insets(20,20,20,20));
        VBox.setMargin(date,new Insets(20,20,20,20));
        VBox.setMargin(imageview,new Insets(20,20,20,20));

        vBox.getChildren().addAll(imageview,date,cap);
        vBox.getStyleClass().add("stack-pane");



        return vBox;
    }
}
