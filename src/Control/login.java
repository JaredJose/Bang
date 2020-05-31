package Control;

import Model.loginDatabaseMethods;

public class login {
	private String user;
	private String pass;
	//Log-in Methods to obtain a user ID
	
	//Run Login UI (constructor)

	public int loginDB() {
		loginDatabaseMethods db = new loginDatabaseMethods();
		return db.getUserID(user, pass);
	}
}
