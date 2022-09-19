package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Arifmetics.SqrtofMinusNumExeption;
import Calculator.Exeptions.Stack.EmptyStackExeption;

import static java.lang.Math.sqrt;

public class Sqrt implements Worker{
    public void execute(Args arg, Context context) throws SqrtofMinusNumExeption, EmptyStackExeption {

        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty");
        double a = context.stack.pop();

        if(a < 0) throw new SqrtofMinusNumExeption("Sqrt of minus number:", a);

        context.stack.push(sqrt(a));
    }
}
