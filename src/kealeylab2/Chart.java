/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kealeylab2;
import java.lang.reflect.Array;
import java.util.*;
//import java.math.*;
/**
 * @author wrkealey0
 * @version 100720191945
 */
public class Chart {
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    char[] cipherText;
    String[] words;
 
    /**
     * Constructs a cryptographic chart to help solve a Caesar Cipher.
     * @param t a Character ArrayList of the encrypted message.
     * @param a an array of words to compare against strings to compare against tests. 
     */
    public Chart(char[] t, String[] a){
        this.cipherText = t;
        this.words = a;
    }
    
    public Chart(char[]t){
        this.cipherText = t;
    }
    
    /**
     * Gets the index of a given character in the alphabet.
     * @param r is the character being searched for.
     * @return is the index of the character being searched. 
     */
    public int getIndex(char r){
        for (int i = 0; i < alphabet.length-1; i++){
            if (r == alphabet[i]){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Finds the position in the alphabet for a given character, and then 
     * performs the math to return the letter n-spaces ahead of that character,
     * cyclic. Returns "*" if the character is not found in the provided alphabet.
     * @param y is a character to search the alphabet for. 
     * @param t is the number to add to the index of the character.
     * @return 
     */
    public String git(char y, int t){
        String c = "";
        int f = getIndex(y);
        if(f==-1){
            return "*";
        }
        /**
         * Using Math.abs(t-f) works except it replaces last letter of the String
         * with the next letter value of k in alphabet. For some reason I don't have time to locate. 
         * So strings return in proper order,
         * but with a, b, c, d... as the last letter for each string down the list.
         * I am reverting to the original solution to use t+f rather than Math.abs(t-f). 
         * This is so I can use it to encrypt clues for Daniel's Christmas Present.
         * Who cares all that much, I have a nearly perfect grade in the class and this project scored 
         * 110/100 with the Extra Credits. (WRK, 10/30/2019)
         */
        int q = (t+f); 
        int h = q%26;
        c += Array.getChar(alphabet, h);
        return c;
    }

    /**
     * This runs all the possible iterations of the Caesar cipher.
     * @return a String[] of all the possible plain-texts. 
     */
    public ArrayList<String> test(){
        ArrayList<String> off = new ArrayList(); 
        for (int i = 0; i < 25; i++){ 
            //off[i]=i+1+": "+test2(i+1);
            off.add(test2(i+1));
        }
        return off;
    }
    
    /**
     * This is a helper method to test() it makes each String to enter into the 
     * array returned in test().
     * @param in an integer to add to the alphabetical index of each input letter.
     * @return a string of either plain-text or gibberish added to the array in test().
     */
    public String test2(int in){
        String t = "";
        for (int j = 0; j<cipherText.length; j++){
                char s = Array.getChar(cipherText, j);
                t+=git(s, in);
                }
        return t;
    }
    
    /**
     * EXTRA CREDIT
     * Takes a string and checks it against an ArrayList of the ~200 most commonly 
     * used English words.
     * @param target is an individual string of 'Plaintext' or gibberish.
     * @return is true if it contains a common word, false otherwise.
     */
    public boolean findWord(String target){
        for (int i = 0; i < words.length; i++){
            if(target.contains(words[i])){
                return true;
        }
        }
        return false;
    }
    
    /**
     * Makes a new ArrayList of strings that contain words from a target list.
     * @param t is an ArrayList to compare with the target list.
     * @return is a new ArrayList with only strings which contain matches.
     */
    public ArrayList<String> org(ArrayList<String> t){
        ArrayList<String> out = new ArrayList();
        for (String s: t){
            if(findWord(s)){
                out.add(s);
            }
        }
        return out;
    }
    /**
     *Overrides toString()
     *@return String version of findings
     */
    @Override
    public String toString(){
        String o = "";
        for(String e : this.test()){
            o += e+"\n";
        }
        return o;
    }
    
   
    public String putSpaces(String t){
        String o ="";
        String o2 ="";
        ArrayList<String> m = new ArrayList();
        for (int i = 0; i<t.length(); i++){
            o+=t.charAt(i);
            if(findWord(o)){
                o+=" ";
                m.add(o);
                o = "";
            }
        }
        for (String s: m){
            o2+=s;
            }
        char t2 = t.charAt(t.length()-1);
            o2+=t2;
       
        return o2;
    }

    public ArrayList<String> spaceTest(ArrayList<String> t){
        String y;
        ArrayList<String> m = new ArrayList();
        for (String s: t){
            y = putSpaces(s);
            m.add(y);
        }
        return m;
    }
  
}