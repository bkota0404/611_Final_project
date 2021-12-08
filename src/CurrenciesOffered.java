import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrenciesOffered implements BankRepository{
    @Override
    public void delete(int id) {

    }

    //list of all the currencies offered as a String
    @Override
    public List getAllList() {
        List<String>  currencyList = Arrays.stream(CurrencyType.values()).map(Enum::name).collect(Collectors.toList());
        return currencyList;
    }
}
