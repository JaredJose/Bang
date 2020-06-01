package Model;

import java.time.LocalDate;
import java.util.ArrayList;

import Control.DateSort;

public class memoryDatabaseMethods {
	//primary objective is to use the userID that was previously obtained
	//to create Memory objects out of the data that is pulled from the DynamoDB
	static int userID;
	ArrayList<Memory> memList = new ArrayList<Memory>();
	
	public memoryDatabaseMethods(int userID) {
		this.userID = userID;
	}
	
	public ArrayList<Memory> queryDB() {
		//connect to the database
		//select based off of the userID field
		//will call buildMem() for each returned row
		return memList; //return the built list to Driver
	}
	
	private void buildMem() {
		Memory tempMem;
		
		//Fill tempMem with row's information
		//pull File from s3 Instance
		//convert Time string into TimeDate using TimeDate.parse
		//store caption String
		
		memList.add(tempMem); //add to the arraylist
		memList.sort(new DateSort()); //sort the arraylist from TimeDate field
	}
	
	private LocalDate stringToTimeDate(String str) {
		return LocalDate.parse(str);
	}
}
