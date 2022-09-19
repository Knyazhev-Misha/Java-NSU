package com.company;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Write_file {
    private HashMap<String, Integer> words;
    private String ptr;
private int count;
    Write_file(HashMap<String, Integer> words, int count){this.words = words; this.count = count;}

    public void write(){
       try(FileWriter writer = new FileWriter("out.csv", false))
       {
           for (HashMap.Entry entry: words.entrySet()) {

               writer.write(entry.getKey().toString());
               writer.write(";");
               writer.write(Double.toString((double) Integer.parseInt(entry.getValue().toString()) / count));
               writer.write(";");
               writer.write(Double.toString((double) Integer.parseInt(entry.getValue().toString()) / count * 100));
               writer.write("%");
               writer.write("\n");

           }

       }
       catch(IOException ex){

           System.out.println(ex.getMessage());
       }
   }
}


