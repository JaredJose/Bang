package Model;

import java.util.ArrayList;

import Control.DateSort;

public class memoryDatabaseMethods {
	//primary objective is to use the userID that was previously obtained
	//to create Memory objects out of the data that is pulled from the DynamoDB
	static int userID;
	ArrayList<Memory> memList = new ArrayList<Memory>();
	
	public memoryDatabaseMethods(int userID) {
		this.userID = userID;
		//connect and query Photos section of db
		//build list using buildMem()
	}
	
	private void buildMem() {
		Memory tempMem;
		
		//Fill tempMem with row's information
		
		memList.add(tempMem);
		memList.sort(new DateSort());
	}
	
	public ArrayList<Memory> getList(){
		return memList;
	}
}
