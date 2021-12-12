import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StocksOffered implements BankRepository{

    private List<Stocks> stocks;
    private List<String> stockPrices;
    private List<String> stockSymbols;
    private List<String> stockNames;
    @Override
    public void delete(int id) {

    }

    @Override
    public List<Stocks> getAllList() {
        return null;
    }

    //public static void main(String[] args) {
    public void getStocksAPI(){
        stockPrices = new ArrayList<>();
        stockSymbols = new ArrayList<>();
        stockNames = new ArrayList<>();
        try {
            URL url = new URL("https://financialmodelingprep.com/api/v3/stock/list?apikey=7046f8d997256cf2effe01c18a29db58");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    //System.out.println(line);
                    if (line.contains("\"symbol\" :")) {
                        stockSymbols.add(line.split(": ")[1].split(",")[0].replace("\"", ""));
                    }

                    if (line.contains("\"price\" : ")) {
                        stockPrices.add(line.split(": ")[1].split(",")[0].replace("\"", ""));
                    }
                    if (line.contains("\"name\" : ")) {
                        stockNames.add(line.split(": ")[1].split(",")[0].replace("\"", ""));
                    }
                }
                //System.out.println(Arrays.toString(stockPrices.toArray()));
                //System.out.println(Arrays.toString(stockNames.toArray()));
                //System.out.println(Arrays.toString(stockSymbols.toArray()));
                ListToFile fileWriter = new ListToFile();
                fileWriter.addToFile(stockNames,stockSymbols,stockPrices,"Stocks.csv",false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public double getLatestStockPriceBySymbol(String stockSymbol){
        getStocksAPI();
        int index = stockSymbols.indexOf(stockSymbol);
        if(index != 0)
            return Double.parseDouble(stockPrices.get(index));
        else
            return 0;
    }
}
