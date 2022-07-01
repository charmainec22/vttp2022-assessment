package task_01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        List<String> csvFileList = new LinkedList<>();
        ArrayList<ArrayList<String>> allFile = new ArrayList<ArrayList<String>>();
        if(args.length > 0){
            File csvFile = new File(args[0]);//e.g hello.csv,bye.csv
            //String templateFile = args[1];
            File templateFile = new File(args[1]);

            
            
            //String[] files = csvFile.split(",");

            // for (int i = 0; i < files.length; i++){
            //     csvFileList.add(files[i]); //add files into list after seperated by the commas
            //     File files = new File(".", files[i]);
            // }

            if(!templateFile.exists() || !templateFile.isFile()){
                throw new IllegalArgumentException("Could not read template file: " + templateFile);
            }

            if(!csvFile.exists() || !csvFile.isFile()){
                throw new IllegalArgumentException("Could not read csv file: " + csvFile);
            }

            String csvLine;
            String tempLine;
            String tempTxt = "";
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while((csvLine = br.readLine()) != null){
                csvFileList.add(csvLine);
            }

            BufferedReader brTemp = new BufferedReader(new FileReader(templateFile));
            while((tempLine = brTemp.readLine()) != null){
                tempTxt += tempLine + "\n";

            }

            for (String i: csvFileList){
                ArrayList<String> templateList = new ArrayList<String>();
                String[] fileSplit = i.split(",");
                for ( int j =0; j < fileSplit.length; j++){
                    templateList.add(fileSplit[j]);
                }
                allFile.add(templateList);
            }

            String finalResult;
            for (int i = 1; i < (allFile.size() - 1); i++){
                finalResult = tempTxt;
                ArrayList<String> header = allFile.get(0);
                for ( int h = 0; h < header.size(); h++){
                        finalResult = finalResult.replaceFirst(header.get(h), allFile.get(i).get(h));
                }
                finalResult = finalResult.replace("\\n", "\n");
                finalResult = finalResult.replace("__", "");
                System.out.println(finalResult);
            }
            //new Main().merge(templateFile, csvFile);
        }

       
       
    }
}