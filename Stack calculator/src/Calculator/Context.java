package Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    public Map<String, Double> defineBuffer = new HashMap<>();
    public Stack<Double> stack = new Stack<>();
}
