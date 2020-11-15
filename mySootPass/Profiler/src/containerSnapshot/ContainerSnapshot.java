package containerSnapshot;

import soot.*;
import soot.baf.*;
import soot.baf.internal.*;
import soot.dava.internal.javaRep.DAssignStmt;
import soot.jimple.internal.JAssignStmt;

import java.util.Map;

public class ContainerSnapshot extends BodyTransformer {
    protected void internalTransform(Body body, String phase, Map options) {
        SootMethod sm = body.getMethod();
        int unitCnt = 0;
        for(Unit unit : body.getUnits()){
//            for(ValueBox usedValueBox : unit.getUseBoxes()){
//                if(usedValueBox.getValue() instanceof Local){
//                    Local usedLocal = (Local) usedValueBox.getValue();
//                }
//                if(unit instanceof InvokeStmt && usedValueBox.getValue().getType().equals(NullType.v())){
//                    System.out.println("    Line " + unit.getJavaSourceStartLineNumber() +": MUST NullPointer usage in unit (" + c +") " + unit);
//                }
//            }
            unitCnt++;
            if (unit instanceof JAssignStmt) {
                System.out.println(unit);
            }
            //System.out.println(unit);
        }
        System.out.println(sm.getName() + ":" + unitCnt);
    }
}
