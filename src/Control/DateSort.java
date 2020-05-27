package Control;

import java.util.Comparator;

import Model.Memory;

public class DateSort implements Comparator<Memory>{
	@Override
	public int compare(Memory one, Memory two) {
		return two.getTime().compareTo(one.getTime());
	}
}
