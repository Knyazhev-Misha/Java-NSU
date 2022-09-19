package com.company;

import java.io.*;


import java.lang.StringBuilder;

public class Read_file {
    private InputStreamReader reader = null;
    private StringBuilder builder = new StringBuilder();
    private String text;

    public void read(){
    try {
        File file = new File("C:/Users/Alena/java/src/com/company/Read_file.java");

        FileReader fr = new FileReader(file);

        BufferedReader reader = new BufferedReader(fr);

        String line; //= reader.readLine();
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            String[] ptr = line.split("^\\w+");
         System.out.println(ptr[1]);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /* public void read(){
        try
        {
            reader = new InputStreamReader(new FileInputStream("C:/Users/Alena/java/src/com/company/Read_file.java"));

            int symbol  = reader.read();

            while(symbol != -1 ){
                builder.append((char)symbol);
                symbol  = reader.read();
            }
            text = builder.toString();
        }
        catch (IOException e)
        {
            System.err.println("Error while reading file" + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
*/

    public StringBuilder GetSrting() {
       return builder;
    }
}
