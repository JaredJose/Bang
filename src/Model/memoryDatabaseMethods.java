package Model;

public class memoryDatabaseMethods {
	//primary objective is to use the userID that was previously obtained
	//to create Memory objects out of the data that is pulled from the DynamoDB
	static int userID;
	
	public memoryDatabaseMethods(int userID) {
		this.userID = userID;
	}
	
}
