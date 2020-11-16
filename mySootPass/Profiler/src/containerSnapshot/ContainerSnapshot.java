package containerSnapshot;

import soot.*;
import soot.baf.*;
import soot.baf.internal.*;
import soot.dava.internal.javaRep.DAssignStmt;
import soot.jimple.internal.*;

import java.util.Map;
import java.util.List;

public class ContainerSnapshot extends BodyTransformer {
    private void updateSnapshotAfterParsing(String exprStr) {
        int index1 = exprStr.indexOf("<");
        int index2 = exprStr.lastIndexOf(">");
        if (index1 == -1) {
            return;
        }
        String subExprStr = exprStr.substring(index1, index2);
        String className = subExprStr.substring(0, subExprStr.indexOf(":"));
        String methodName = subExprStr.substring(subExprStr.indexOf(":") + 1);

        if (className.contains("Vector")) {
            Snapshot.hitContainerMethod("Vector", methodName);
        } else if (className.contains("Stack")) {
            Snapshot.hitContainerMethod("Stack", methodName);
        } else if (className.contains("HashMap")) {
            Snapshot.hitContainerMethod("HashMap", methodName);
        }
    }

    protected void internalTransform(Body body, String phase, Map options) {
        SootMethod sm = body.getMethod();
        for(Unit unit : body.getUnits()){
            System.out.println(unit);
            if (unit instanceof JAssignStmt) {
                JAssignStmt assignUnit = (JAssignStmt) unit;
                System.out.println(assignUnit);
                Value rValue = assignUnit.getRightOp();
                updateSnapshotAfterParsing(rValue.toString());
            } else if (unit instanceof JInvokeStmt) {
                JInvokeStmt invokeStmt = (JInvokeStmt) unit;
                System.out.println(invokeStmt);
                updateSnapshotAfterParsing(invokeStmt.toString());
            }
            System.out.println("");
        }
    }
}
