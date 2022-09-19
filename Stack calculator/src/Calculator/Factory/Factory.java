package Calculator.Factory;

import Calculator.Worker.Worker;

import java.io.*;

public class Factory {
    public Worker createWorker( String classname)  {

        String classWorker = getClassWorker(classname);

        Worker worker = null;
        Class ptr;
        try {
           ptr = Class.forName(classWorker);
           worker =(Worker)ptr.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
           e.printStackTrace();
        }
        return worker;
    }

    public String getClassWorker( String classname){
        InputStream resourceAsStream;
        resourceAsStream = Factory.class.getResourceAsStream("configWorker.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = null;

        try {
            while ((line = reader.readLine()) != null && !classname.equals(line.substring(0, line.indexOf(" "))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line.substring(line.indexOf(" ") + 1, line.length());
    }

}
