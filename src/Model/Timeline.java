import java.util.*;

public class Timeline
{
	private ArrayList<Photo> t = new ArrayList<Photo>();

	public void addPhoto(String photoName)
	{
		Photo p = //need code to fetch photo from database
		t.add(p);
	}

	public void removePhoto(String photoName)
	{
		//also need code to remove photo drom database
		
		for(int i : t)
		{
			if((photo.getName()).equals(photoName))
			{
				t.remove(i);
			}
		}
	}
}
