package Model;

import java.io.File;
import java.time.LocalDate;

public class Memory {
	File pt;
	LocalDate time;
	String caption;
	
	public Memory(File pt, LocalDate time, String capt) {
		this.pt = pt;
		this.time = time;
		caption = capt;
	}
	
	public Memory(LocalDate time, String capt) {
		this.time = time;
		caption = capt;
	}
	
	public LocalDate getTime() {
		return time;
	}
	
	public File getPhoto() {
		return pt;
	}
	
	public String getCaption() {
		return caption;
	}
	
	@Override
	public String toString() {
		return "[Date: " + time + " Caption: " + caption + "]";
	}
}
