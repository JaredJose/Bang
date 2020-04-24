package Model;

public class DatabaseMethods {
	
	public static Photo getPhoto(String photoName) {
		
		Photo newPhoto = new Photo();
		
		newPhoto.setName(photoName);
		newPhoto.setDescription("");	//Database communication
		newPhoto.setPath("");		//Database communication
		
		return newPhoto;
	}
	
	public static void savePhoto(String photoName) {
		
		//Database communication
		
	}
	
	public static void removePhoto(String photoName) {
		
		//Database communication
		
	}
}
