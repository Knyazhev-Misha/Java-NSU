package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Args.DefineException;

public class Define implements Worker {
    public void execute(Args arg, Context context) throws DefineException {

        if(context.defineBuffer.containsKey(arg.arg_1))
            throw new DefineException("This exist", arg.arg_1);

        context.defineBuffer.put(arg.arg_1, Double.parseDouble(arg.arg_2));
    }
}
