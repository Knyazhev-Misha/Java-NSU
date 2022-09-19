package Calculator;

import Calculator.Exeptions.Args.DefineException;
import Calculator.Exeptions.Args.NullArgsExeption;
import Calculator.Exeptions.Arifmetics.DivideZeroExeption;
import Calculator.Exeptions.Arifmetics.SqrtofMinusNumExeption;
import Calculator.Exeptions.Stack.EmptyStackExeption;
import Calculator.Worker.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void Define(){
    Context context = new Context();
        Args arg = new Args();
        arg.arg_1 = "a";
        arg.arg_2 = "4";
        Define def = new Define();

        try {
            def.execute(arg, context);
        } catch (DefineException e) {
            e.printStackTrace();
        }
        Map<String, Double> expectedBuffer = new HashMap<>();
        expectedBuffer.put("a", (double)4);
        Assert.assertEquals(context.defineBuffer,expectedBuffer);
    }

    @Test
    public void Divide(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(8.0);
        context.stack.push(32.0);

        Divide div = new Divide();

        try {
            div.execute(arg, context);
        } catch (DivideZeroExeption | EmptyStackExeption e) {
            e.printStackTrace();
        };
        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(4.0));
    }

    @Test
    public void Minus(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(8.0);
        context.stack.push(32.0);

        Minus min = new Minus();

        try {
            min.execute(arg, context);
        } catch (EmptyStackExeption e) {
            e.printStackTrace();
        };

        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(24.0));
    }

    @Test
    public void Multiply(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(8.0);
        context.stack.push(3.0);

        Multiply mul = new Multiply();

        try {
            mul.execute(arg, context);
        } catch (EmptyStackExeption e) {
            e.printStackTrace();
        };

        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(24.0));
    }

    @Test
    public void Plus(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(8.0);
        context.stack.push(32.0);

        Plus plus = new Plus();

        try {
            plus.execute(arg, context);
        } catch (EmptyStackExeption e) {
            e.printStackTrace();
        };

        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(40.0));
    }

    @Test
    public void Pop(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(8.0);

        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(8.0));

        Pop pop = new Pop();

        try {
            pop.execute(arg, context);
        } catch (EmptyStackExeption e) {
            e.printStackTrace();
        };

        Assert.assertEquals(context.stack.empty(),true);
    }

    @Test
    public void Print() {
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(8.0);

        Print print = new Print();

        try {
            print.execute(arg, context);
        } catch (EmptyStackExeption e) {
            e.printStackTrace();
        };

        String file = "out.txt";
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
            line = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertThat(Double.parseDouble(line),equalTo(8.0));
    }

    @Test
    public void Push(){
        Context context = new Context();
        Args arg = new Args();
        context.defineBuffer.put("a", 4.0);
        arg.arg_1 = "a";


        Push push = new Push();

        try {
            push.execute(arg, context);
        }  catch (NullArgsExeption e) {
            Assert.assertEquals(context.stack.empty(),true);
        }
        ;

        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(4.0));
    }

    @Test
    public void Sqrt(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(4.0);

        Sqrt sqrt = new Sqrt();

        try {
            sqrt.execute(arg, context);
        } catch (SqrtofMinusNumExeption| EmptyStackExeption e) {
            e.printStackTrace();
        };

        double a = context.stack.peek();
        Assert.assertThat(a,equalTo(2.0));
    }

    @Test
    public void Error(){
        Context context = new Context();
        Args arg = new Args();
        context.stack.push(0.0);
        context.stack.push(2.0);

        Divide div = new Divide();
        String err = "Divide on Zero";

        try {
            div.execute(arg, context);
        } catch (DivideZeroExeption | EmptyStackExeption e) {
            Assert.assertEquals(e.getMessage(),err);
        };
    }
}