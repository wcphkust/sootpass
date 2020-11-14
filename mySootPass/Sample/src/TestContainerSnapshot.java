import java.util.Stack;

public class TestContainerSnapshot {
    public static void main(String []args) {
        Integer a1  = null;
        Integer a2 = new Integer(2);
        Stack<Integer> st = new Stack<>();
        st.push(a1);
        st.push(a2);
        System.out.println(a1.intValue());
        Integer b2 = st.pop();
        Integer b1 = st.pop();
        System.out.println(b2.intValue());
        System.out.println(b1.intValue());
    }
}