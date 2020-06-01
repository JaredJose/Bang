package Control;

import Model.loginDatabaseMethods;

public class login {
	private String user;
	private String pass;
	//Log-in Methods to obtain a user ID
	
	//Run Login UI (constructor)

	public int getUserID() {
		loginDatabaseMethods db = new loginDatabaseMethods();
		int uID = db.getUserId(user,pass);
		if(uID == -1) {
			//put "incorrect login credentials" message
			//await user re-entering login
		} else
			return uID;
	}
}
