import java.util.List;

public class UserCreation implements BankRepository{
    @Override
    public void delete(int id) {

    }

    @Override
    public List getAllList() {
        return null;
    }


    public boolean createUser(String name, String username, String password, UserRoles userRole, DBManager dbManager) {
        boolean isCreated = dbManager.addUser(name,username,password,userRole);
        if(isCreated)
            return true;
        else
            return false;
    }
}
