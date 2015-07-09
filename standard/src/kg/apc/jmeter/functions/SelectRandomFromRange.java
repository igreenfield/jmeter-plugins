package kg.apc.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.threads.JMeterVariables;

import java.security.SecureRandom;
import java.util.*;

public class SelectRandomFromRange extends AbstractFunction {
    private static final List<String> desc = new LinkedList<String>();
    private static final String KEY = "__SelectRandomFromRange";

    static {
        desc.add("Range start (inclusive)");
        desc.add("Range end (inclusive)");
        desc.add("Number of item to select");
        desc.add("Separator");
        desc.add("Result type per Item (int|string)");
        desc.add("Name of variable in which to store the result (optional)");
    }

    private Object[] values;

    public SelectRandomFromRange() {
    }

    @Override
    public synchronized String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {

        int start = Integer.parseInt(getParameter(0));
        int end = Integer.parseInt(getParameter(1));

        int count = Integer.parseInt(getParameter(2));

        HashSet<Integer> integerHashSet = new HashSet();

        String separator = getParameter(3);
        String type = getParameter(4);

        Random random = new Random(System.currentTimeMillis());

        if (count >= end - start + 1){
         for (int i = start; i <= end; i++){
             integerHashSet.add(i);
         }
        } else {
            while (integerHashSet.size() < count) {
                int number = random.nextInt(end - start) + start;
                if (!integerHashSet.contains(number))
                    integerHashSet.add(number);
            }
        }

        StringBuilder result = new StringBuilder();
        for (Integer num: integerHashSet) {
            if (type.equals("string"))
                result.append("\"");
            result.append(num).append(separator);
            if (type.equals("string"))
                result.append("\"");
        }

        result.setLength(result.length() - 1);

        JMeterVariables vars = getVariables();
        if (vars != null && values.length > 5) {
            String varName = getParameter(5).trim();
            vars.put(varName, result.toString());
        }

        return result.toString();
    }

    @Override
    public synchronized void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        checkMinParameterCount(parameters, 5);
        values = parameters.toArray();
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }

    private String getParameter(int i) {
        return ((CompoundVariable) values[i]).execute();
    }
}
