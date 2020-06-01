package Control;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Memory;
import Model.memoryDatabaseMethods;
import View.TimelineUI;

public class Driver {
	 static ArrayList<Memory> MemoryList = new ArrayList<>();
	 private static int userID;
	
	public void addMemory(Memory mem) {
		MemoryList.add(mem); //adding a memory to the list
		MemoryList.sort(new DateSort()); //automatically sorts the list everytime a memory is added
	}
	
	public static ArrayList<Memory> getList(){
		return MemoryList;
	}
	
	public static void main(String[] args) {
		//User Log-In (get uID)
	    login begin = new login();
	    userID = begin.getUserID();
	    
	    //Build MemoryList off of uID
	    memoryDatabaseMethods db = new memoryDatabaseMethods(userID); //constructor we count number of memories
	    MemoryList = db.queryDB();
	    
	    //@Andrei, however you want to deal with accepting the MemoryList
	    //Display Memories on TimeLine
	    TimelineUI view = new TimelineUI(MemoryList);
	    view.reveal();
	}
}
