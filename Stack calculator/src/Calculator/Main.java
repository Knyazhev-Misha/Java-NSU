package Calculator;

import Calculator.Exeptions.Args.ArgsExeption;
import Calculator.Exeptions.Args.DefineException;
import Calculator.Exeptions.Args.NullArgsExeption;
import Calculator.Exeptions.Arifmetics.Arifmetics;
import Calculator.Exeptions.Arifmetics.DivideZeroExeption;
import Calculator.Exeptions.Arifmetics.SqrtofMinusNumExeption;
import Calculator.Exeptions.Stack.EmptyStackExeption;

import Calculator.Exeptions.Stack.StackExeption;
import Calculator.Worker.Worker;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;


public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("My_log");
        FileHandler fh = null;
        try {
            fh = new FileHandler("log.txt", true);
            LogManager.getLogManager().reset();
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String file = "in.txt";
        // String file = null;

        int count = 1;
        HashMap<Integer, Worker> queue = new HashMap<Integer, Worker>();
        ArrayList<Args> arg = new ArrayList<Args>();
        Context context = new Context();
        Parse text = new Parse();

        if (file != null) {

            text.parse(file, queue, arg, logger, null);
            for (Args iterator : arg) {

                try {
                    queue.get(count).execute(iterator, context);
                    count += 1;
                } catch (DivideZeroExeption | SqrtofMinusNumExeption | NullArgsExeption | EmptyStackExeption | DefineException e) {
                    e.printStackTrace();
                }
            }
        } else {

            String classname;
            Scanner console = new Scanner(System.in);
            String operator = console.nextLine();

            while (!operator.equals("ex")) {
                text.parse(file, queue, arg, logger, operator);
                for (Args iterator : arg) {

                    try {
                        queue.get(count).execute(iterator, context);
                    }
                    catch (Arifmetics e){
                        e.printStackTrace();
                    }
                    catch (ArgsExeption e){
                        e.printStackTrace();
                    }
                    catch (StackExeption e){
                        e.printStackTrace();
                    }

                }

                operator = console.nextLine();
                arg.clear();
                queue.clear();
            }
        }
    }
}
