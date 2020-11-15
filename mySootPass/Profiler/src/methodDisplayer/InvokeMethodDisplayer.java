package methodDisplayer;

import soot.*;
import soot.jimple.InvokeStmt;
import soot.toolkits.graph.TrapUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.Chain;

import java.util.Map;

public class InvokeMethodDisplayer extends BodyTransformer {
    protected void internalTransform(Body body, String phase, Map options) {
        SootMethod sm = body.getMethod();
        int unitCnt = 0;
        MethodSet.MethodSet.add(sm.getName());
    }
}
