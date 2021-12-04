/**
 * Receiving information from UI to create user
 * @author Qingyuan Zhang
 *
 */
public class UserCreation {
	
	DBManager dbManager;
	
	public UserCreation(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public boolean createUser(String name, UserRoles userRole, String username, String password) {
		// TO-DO
		return true;
	}
}
