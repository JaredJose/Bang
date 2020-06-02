package Control;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Memory;
import Model.memoryDatabaseMethods;
import View.TimelineUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Driver extends Application {
	 static ArrayList<Memory> MemoryList = new ArrayList<>();
	 private static int userID;
	 private static Stage primaryStage;
	 private static Pane mainLayout;
	 public static boolean isUploadAdded;
	
	public void addMemory(Memory mem) {
		MemoryList.add(mem); //adding a memory to the list
		MemoryList.sort(new DateSort()); //automatically sorts the list everytime a memory is added
	}
	
	public static ArrayList<Memory> getList(){
		return MemoryList;
	}
	
	public static void main(String[] args) throws Exception {

		launch(args);
	}

	public static void setUID(int uID)
	{
		userID = uID;
	}

	public static int getUID()
	{
		return userID;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage = stage;
		this.primaryStage.setTitle("Login");


		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(_mainUIControl.class.getResource("/View/LoginUI.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();


		memoryDatabaseMethods db = new memoryDatabaseMethods(userID); //constructor we count number of memories
		MemoryList = db.queryDB();

	}

	public static boolean getIsUploadAdded()
	{
		return isUploadAdded;
	}

	public static void setIsUploadAdded(boolean b)
	{
		isUploadAdded = b;
	}

}
