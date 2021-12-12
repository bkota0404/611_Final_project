import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ListToFile {

    public void addToFile(List<String> content1,List<String> content2,List<String> content3, String filename, boolean overWrite){
        String lineSep = System.getProperty("line.separator");
        String output = "Stock Name"+"\t"+"Symbol"+"\t"+"Price";
        try{
            String filepath = System.getProperty("user.dir") + "/src/data/"+filename;
            File newfile = new File(filepath);
            newfile.createNewFile(); // if file already exists will do nothing
            //System.out.println(Arrays.toString(content.toArray()));
            for(int i=0;i<content1.size();i++){
                //System.out.println(content.get(i));
                output+="\n"+content1.get(i)+"\t"+content2.get(i)+"\t"+content3.get(i);
            }
            //System.out.println(output);
            FileWriter fw = new FileWriter(filepath);
            fw.write(output);
            fw.flush();
            fw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
