import java.util.List;

public interface BankRepository<T> {

    public void delete(int id);
    public List<T> getAllList();
}
