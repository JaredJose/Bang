public class Photo
{
	private String name;
	private String path;
	private String description;

	public Photo(String n, String p, String d)
	{
		this.name = n;
		this.path = p;
		this.description = d;
	}

	public String getName()
	{
		return name;
	}

	public String getPath()
	{
		return path;
	}

	public String getDescription()
	{
		return description;
	}

	public void setName(String n)
	{
		this.name = n;
	}

	public void setPath(String p)
	{
		this.path = p;
	}

	public void setDescription(String d)
	{
		this.description = d;
	}
}
