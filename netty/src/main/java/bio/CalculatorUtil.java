package bio;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * TODO:
 *
 * @author zhijian.zheng@ucarinc.com
 * @package: bio
 * @date: 2020-09-12 11:33
 **/
public class CalculatorUtil {
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public static Object cal(String expression){
        try {
            return jse.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }
}
