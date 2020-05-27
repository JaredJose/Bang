package Control;

import java.util.ArrayList;

import Model.Memory;

public class Driver {
	ArrayList<Memory> MemoryList = new ArrayList<Memory>();
	
	public void addMemory(Memory mem) {
		MemoryList.add(mem); //adding a memory to the list
		MemoryList.sort(new DateSort()); //automatically sorts the list everytime a memory is added
	}
	
	public ArrayList<Memory> getList(){
		return MemoryList;
	}
}
