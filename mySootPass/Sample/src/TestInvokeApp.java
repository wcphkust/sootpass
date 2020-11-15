import java.util.Stack;

public class TestInvokeApp {
    public static void main(String []args) {
        Stack<TestInvoke> s = new Stack<>();
        TestInvoke invoke = new TestInvoke();
        invoke.methodA();
        s.add(invoke);
        invoke = s.pop();
        invoke.methodA();
    }
}
