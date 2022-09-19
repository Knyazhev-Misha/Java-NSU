package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Stack.EmptyStackExeption;

import java.io.FileWriter;
import java.io.IOException;

public class Print implements Worker{
    public void execute(Args arg, Context context) throws EmptyStackExeption {

        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty");
        double a = context.stack.peek();

        try(FileWriter writer = new FileWriter("out.txt", false))
        {
            writer.write(Double.toString(a));
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

}
