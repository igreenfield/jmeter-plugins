package kg.apc.jmeter.functions;

import kg.apc.emulators.TestJMeterUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SelectRandomFromRangeTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        TestJMeterUtils.createJmeterEnv();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testExecute() throws Exception {
        System.out.println("execute 1");
        Collection<CompoundVariable> parameters = new ArrayList<CompoundVariable>();
        parameters.add(new CompoundVariable("1"));
        parameters.add(new CompoundVariable("5"));
        parameters.add(new CompoundVariable("5"));
        parameters.add(new CompoundVariable(","));
        parameters.add(new CompoundVariable("int"));
        SelectRandomFromRange instance = new SelectRandomFromRange();
        instance.setParameters(parameters);
        String result = instance.execute(null, null);
        assertTrue(result.split(",").length == 5);
    }

    /**
     * Test of setParameters method, of class Substring.
     */
    @Test
    public void testSetParameters() throws Exception {
        System.out.println("setParameters");
        Collection<CompoundVariable> parameters = new ArrayList<CompoundVariable>();
        parameters.add(new CompoundVariable("1"));
        parameters.add(new CompoundVariable("3"));
        parameters.add(new CompoundVariable("1"));
        parameters.add(new CompoundVariable("2"));
        parameters.add(new CompoundVariable("2"));
        SelectRandomFromRange instance = new SelectRandomFromRange();
        instance.setParameters(parameters);
        instance.execute(null, null);
    }

    /**
     * Test of getReferenceKey method, of class Substring.
     */
    @Test
    public void testGetReferenceKey() {
        System.out.println("getReferenceKey");
        SelectRandomFromRange instance = new SelectRandomFromRange();
        String expResult = "__SelectRandomFromRange";
        String result = instance.getReferenceKey();
        assertEquals(expResult, result);

    }

    /**
     * Test of getArgumentDesc method, of class Substring.
     */
    @Test
    public void testGetArgumentDesc() {
        System.out.println("getArgumentDesc");
        SelectRandomFromRange instance = new SelectRandomFromRange();
        List result = instance.getArgumentDesc();
        assertEquals(6, result.size());
    }
}
