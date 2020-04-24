package Model;

public class User
{
	private String username;
	private String password;

	public User(String uname, String passwd)
	{
		this.username = uname;
		this.password = passwd;
	}

	public void setUsername(String name)
	{
		this.username = name;
	}

	public void setPassword(String passwd)
	{
		this.password = passwd;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}
}
