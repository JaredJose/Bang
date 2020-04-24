package Model;

import java.util.*;

public class Timeline
{
	private ArrayList<Photo> t = new ArrayList<Photo>();

	public void addPhoto(String photoName)
	{
		Photo p = DatabaseMethods.getPhoto(photoName);
		t.add(p);
	}

	public void removePhoto(String photoName)
	{
		for(Photo i : t)
		{
			if((i.getName()).equals(photoName))
			{
				t.remove(i);
			}
		}
		
		DatabaseMethods.removePhoto(photoName);
	}
}
