package View;

import Model.Memory;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
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

        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        hbox.setPrefSize(1200,800);

        VBox btnArea = new VBox();

        Button backBtn = new Button("Back to Home");
        btnArea.getChildren().add(backBtn);

        Button addBtn = new Button("+");
        btnArea.getChildren().add(addBtn);
        addBtn.setOnAction((final ActionEvent e) -> {
            imageSrc = openFile(primaryStage);
            if (imageSrc != null) {

                Image userUpload = new Image(imageSrc.toURI().toString());
                ImageView imageFrame = new ImageView(userUpload);

                VBox memory = createDummyMem(imageFrame);
                HBox.setMargin(memory, new Insets(200, 0, 400, 0));

                Line hline = createLine();
                HBox.setMargin(hline, new Insets(400, 0, 400, 0));

                hbox.getChildren().add(memory);
                hbox.getChildren().add(hline);
            }
        });


        hbox.getChildren().add(btnArea);

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

    public VBox createDummyMem(ImageView image)
    {
        VBox vBox = new VBox(20);

        Label cap = new Label("BRUH!!!!!!!");

        Label date = new Label("2020-5-27");

        ImageView imageview = image; //Don't exactly know how file is going to work, so i just put imageSrc.toString();
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

    public Line createLine()
    {
        Line line = new Line(0, 200, 300, 200);
        return(line);
    }

    public File openFile(Stage primaryStage)
    {
        FileChooser fileChooser = new FileChooser();

        return(fileChooser.showOpenDialog(primaryStage));
    }

}
