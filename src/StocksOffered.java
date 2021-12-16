import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class StocksOffered implements BankRepository{

    private List<Stocks> stocks;
    private List<String> stockPrices;
    private List<String> stockSymbols;
    private List<String> stockNames;

    public List<String> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(List<String> stockPrices) {
        this.stockPrices = stockPrices;
    }

    public List<String> getStockSymbols() {
        return stockSymbols;
    }

    public void setStockSymbols(List<String> stockSymbols) {
        this.stockSymbols = stockSymbols;
    }

    public List<String> getStockNames() {
        return stockNames;
    }

    public void setStockNames(List<String> stockNames) {
        this.stockNames = stockNames;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Stocks> getAllList() {
        return null;
    }

    //read from csv and return 2dList
    public ArrayList<ArrayList<String>> get2dListOfStock(){
        ArrayList<ArrayList<String>> stockList = new ArrayList<ArrayList<String>>();
        try {
            Scanner scanner = new Scanner(new File(BankConstants.BANK_FILE_PATH+"data/Stocks.csv"));
            String line = scanner.nextLine();//to skip first line
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                String[] content = line.split("\t");//I assume there are no empty columns
                //System.out.printf("%-15s %s%n",content[0],content[1]);
                ArrayList<String> eachList = new ArrayList<>();
                for(String eachCell:content){
                    eachList.add(eachCell);
                }
                stockList.add(eachList);
            }
            //System.out.println(stockList.get(1).get(0));
            scanner.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;
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
