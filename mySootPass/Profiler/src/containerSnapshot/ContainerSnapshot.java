package containerSnapshot;

import soot.*;

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
            System.out.println(unit);
        }
        System.out.println(sm.getName() + ":" + unitCnt);
    }
}