package Calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import Calculator.Factory.Factory;
import Calculator.Worker.Worker;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Parse {
    public void parse(String file, HashMap<Integer, Worker> queue, ArrayList<Args> arg,  Logger logger, String operator) {
        if(file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
                String line, classname;
                Factory factory = new Factory();
                int i = 1;


                while ((line = reader.readLine()) != null) {
                    classname = Parse.parse(line, arg);
                    logger.info("add in execution queue " + classname);
                    queue.put(i, factory.createWorker(classname));
                    i += 1;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            String classname;
            Factory factory = new Factory();
            int i = 1;

            classname = Parse.parse(operator, arg);
            logger.info("add in execution queue " + classname);
            queue.put(i, factory.createWorker(classname));
            i += 1;
        }


    }

    static private String parse(String line, ArrayList<Args> arg) {
        Args ptr = new Args();
        String classname, arg_s, arg_1, arg_2;

        if (line.indexOf(" ") != -1) {
            classname = line.substring(0, line.indexOf(" "));
            arg_s = line.substring(line.indexOf(" ") + 1);

            if (arg_s.indexOf(" ") != -1) {

                arg_1 = arg_s.substring(0, arg_s.indexOf(" "));
                arg_2 = arg_s.substring(arg_s.indexOf(" ") + 1);

                ptr.arg_1 = arg_1;
                ptr.arg_2 = arg_2;
            } else {
                ptr.arg_1 = arg_s;
                ptr.arg_2 = null;
            }
        } else {
            ptr.arg_1 = null;
            ptr.arg_2 = null;

            classname = line;
        }

        arg.add(ptr);
        return classname;
    }
}