package Eternity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EternityEquationManager {
    public static HashMap<String, Double> variables = new HashMap<>();

    public EternityEquationManager() {

    }

    private void addVariable(String name, Double value){
        variables.put(name, value);
    }

    public void test(double x, double y){
//        this.addVariable("x", x);
//        this.addVariable("y", y);
//
//        ExpressionBuilder expression = new ExpressionBuilder("x + y");
//        expression.variables(variables.keySet());
//        Expression builtexpression = expression.build();
//        Iterator it = variables.entrySet().iterator();
//        while(it.hasNext()){
//            Map.Entry pair = (Map.Entry)it.next();
//            builtexpression.setVariable((String)pair.getKey(), (Double)pair.getValue());
//            it.remove();
//        }
//
//        System.out.println(builtexpression.evaluate());

        String test = "2<a><b>cos(12<c>)+9<var>/6log10d*ln(<a>5!)-pi";
        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(test);
        ArrayList<String> al = new ArrayList<>();
        while(matcher.find()){
            al.add(matcher.group(1));
        }
        Set<String> hs = new HashSet<>(al);
        ArrayList<String> varNames = new ArrayList<>(hs);

        for (String str : varNames){
            System.out.println(str);
        }


    }


}
