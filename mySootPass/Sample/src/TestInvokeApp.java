import java.util.Vector;

public class TestInvokeApp {
    public static void main(String []args) {
        Vector<TestInvoke> s = new Vector<>();
        TestInvoke invoke = new TestInvoke();
        invoke.methodA();
        s.add(invoke);
        invoke = s.lastElement();
        invoke.methodA();

        Vector<Integer> intVec = new Vector<>();
        intVec.add(new Integer(1));
        intVec.add(new Integer(2));
        Integer res = new Integer(intVec.get(0).intValue() + intVec.lastElement().intValue());
    }
}
