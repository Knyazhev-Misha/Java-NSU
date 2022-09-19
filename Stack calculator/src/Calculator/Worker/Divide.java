package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Arifmetics.DivideZeroExeption;
import Calculator.Exeptions.Stack.EmptyStackExeption;

public class Divide implements Worker{
    public void execute(Args arg, Context context) throws DivideZeroExeption, EmptyStackExeption {
        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty");
        double a = context.stack.pop();

        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty for second argument");
        double b = context.stack.pop();

        if(b == 0) throw new DivideZeroExeption("Divide on Zero");

        context.stack.push(a / b);
    }
}
