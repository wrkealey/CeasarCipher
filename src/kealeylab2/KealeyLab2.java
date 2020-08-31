/*
 * This project is about taking an input file (which contains an input of a string of 
 * characters) which is <ideally> in the Caesar Cipher. It outputs all possible 
 * plain text given that we do not know the key.
 */
package kealeylab2;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * @author wrkealey0
 * @version 100720191759
 */
public class KealeyLab2 {

 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        String input = "";
        char[] cipherText;
        try{
        File tFile = new File("./KealeyLab2Input.txt");
        FileInputStream in = new FileInputStream(tFile);
        Scanner reader = new Scanner(in, "UTF-8");
        String stream = "";
        while (reader.hasNext()){
            stream+=reader.next()+" ";
        }
        for (int i = 0; i<stream.length(); i++){
            input+=stream.charAt(i);
        }
        String c = input.toUpperCase();
        cipherText = c.toCharArray();
        
        ArrayList<String> w = new ArrayList<String>();
        /**
         * About here we get our word list to determine if there are
         * English words in the potential plaintext. This is done with a list of the 200 
         * most commonly used English words. This list is loosely altered as by this point 
         * I knew what my plaintext contained. I also removed (most) one and two letter words, 
         * as they would return a lot of false positives. 
         */
        File wFile = new File("./PopWordList.txt");
        FileInputStream in2 = new FileInputStream(wFile);
        Scanner readerWord =  new Scanner(in2, "UTF-8");
        while (readerWord.hasNext()){
            String indWord = readerWord.next();
            w.add(indWord.toUpperCase());  
        }
        String[] b = new String[w.size()];
        for (int i = 0; i<w.size(); i++){
            b[i]=w.get(i);
        }
        Arrays.sort(b);
        Chart tChart = new Chart(cipherText, b);
        System.out.println(tChart.toString());
        String out = tChart.toString();
 
        Files.deleteIfExists(Paths.get("./KealeyLab2Output.txt"));
        File o = new File("./KealeyLab2Output.txt"); 
        FileWriter f = new FileWriter(o.getAbsoluteFile(), true);
        PrintWriter b2 = new PrintWriter(f);
        b2.write("Program by William Kealey\n");
        b2.write("Possible plaintext for: "+c+"from: "+tFile.getPath()+"\n\n");
        b2.write(out);
        b2.write("\n\n");
        b2.write("These \"plaintexts\" or gibberish lines contain plausible English (at least one common English word):"+"\n\n");
       
       ArrayList<String> it = tChart.org(tChart.test());
       System.out.println("The values contain English words with possible meaning:\n");
       for(int i = 0; i<it.size(); i++){
           System.out.print(it.get(i)+"\n");
           b2.write(it.get(i)+"\n");
       }
    
       
       b2.write("\n\nHere is an approximation of the plain text with spaces: \n\n");
       System.out.println("\nThese are sentences with spaces added, showing word recognition:");
       ArrayList<String> wSpaces = tChart.spaceTest(tChart.org(tChart.test()));
        System.out.println();
        for (int i = 0; i<wSpaces.size(); i++){
            b2.write(wSpaces.get(i)+"\n");
            System.out.println(wSpaces.get(i));
            }
        b2.close();
        } catch (IOException d){
            System.out.println("Failed to locate input file (or running Jar file).");
            System.out.print("You may manually enter input (does not offer word delimination): ");
            Scanner t = new Scanner(System.in);
            String c = t.nextLine().toUpperCase();
            char[] cipherTextAlt = c.toCharArray();
            Chart manualChart = new Chart(cipherTextAlt);
            ArrayList<String> manualResults = manualChart.test();
            System.out.println(manualChart.toString());
            
            Scanner rT = new Scanner(System.in);
            System.out.print("Run again (Y/N)? ");
            if (rT.nextLine().equalsIgnoreCase("Y")){
                KealeyLab2.main(args);
            } else if (rT.nextLine().equalsIgnoreCase("n")){
                System.out.println("Closing program...");
                System.exit(0);
            }
        }
    }
}