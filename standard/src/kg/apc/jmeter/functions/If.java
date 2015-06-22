package kg.apc.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adizek on 22/06/2015.
 */
public class If extends AbstractFunction {
    private static final List<String> desc = new LinkedList<String>();
    private static final String KEY = "__if";

    static {
        desc.add("Actual value");
        desc.add("Expected value");
        desc.add("Result if actual == expected");
        desc.add("Result if actual != expected");
    }

    private Object[] values;

    public If() {
    }

    @Override
    public synchronized String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {

        String actual = getParameter(0);
        String expected = getParameter(1);

        if (actual.equals(expected)) {
            return getParameter(2).toString();
        } else {
            return getParameter(3).toString();
        }

    }

    @Override
    public synchronized void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        checkMinParameterCount(parameters, 4);
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
