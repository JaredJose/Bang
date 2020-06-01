package Control;

import Model.loginDatabaseMethods;

public class login {
	private String user;
	private String pass;
	
	public login() {
	}

	public int getUserID() {
		//run Login View
		//@Andrei, however LoginUI is supposed to work
		LoginUI frame = new LoginUI();
		frame.visible = true;
		
		//on login button press run code below @Andrei
		loginDatabaseMethods db = new loginDatabaseMethods();
		int uID = db.getUserID(user,pass); //getUserID() returns the userId associated with login credentials
		if(uID == -1) {
			//put "incorrect login credentials" message
			//await user re-entering login
		} else {
			frame.visible = false;
			return uID;
		}
	}
}
