import java.util.List;

public class UserCreation implements BankRepository{
    @Override
    public void delete(int id) {

    }

    @Override
    public List getAllList() {
        return null;
    }


    public static boolean createUser(String name, UserRoles userRole, String username, String password) {
            // TO-DO
            return true;
    }
}
