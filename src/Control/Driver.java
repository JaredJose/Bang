package Control;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.Memory;

public class Driver {
	 static ArrayList<Memory> MemoryList = new ArrayList<>();
	 private int userID;
	
	public void addMemory(Memory mem) {
		MemoryList.add(mem); //adding a memory to the list
		MemoryList.sort(new DateSort()); //automatically sorts the list everytime a memory is added
	}
	
	public static ArrayList<Memory> getList(){
		return MemoryList;
	}
	
	public static void main(String[] args) {
		/*
		Driver testRun = new Driver();
		LocalDate today = LocalDate.now();
		Memory mem1 = new Memory(today, "today"); 
		Memory mem2 = new Memory(today.minusDays(1), "yesterday"); 
		Memory mem3 = new Memory(today.plusDays(1), "tomorrow"); 
		
	    testRun.addMemory(mem3);
	    testRun.addMemory(mem2);
	    testRun.addMemory(mem1);
	    
	    System.out.println(testRun.MemoryList);
	    */
	    login begin = new login();
	    userID = begin.loginDB();
	    
	    memoryDatabaseMethods db = new memoryDatabaseMethods(userID); //constructor we count number of memories
	    
	    while(db.count) {
	    	addMemory(db.getMemory());
	    }
	    
	    TimelineUI view = ew TimelineUI(MemoryList);
	    view.reveal();
	}
}
