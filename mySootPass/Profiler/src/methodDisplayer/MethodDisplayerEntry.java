package methodDisplayer;
import soot.*;
import soot.options.Options;
import java.io.File;
import java.util.*;

/*
Reference:
		add phases to transformer pack by call Pack.add
		Scene Transformer: https://www.sable.mcgill.ca/soot/doc/soot/SceneTransformer.html
		Body Transformer: https://www.sable.mcgill.ca/soot/doc/soot/BodyTransformer.html
 */
public class MethodDisplayerEntry {
	public static void main(String[] args) {
		String[] sootArg = new String[2];

		Options.v().set_soot_classpath(Scene.v().defaultClassPath());
		Pack jtp = PackManager.v().getPack("jtp");
		MethodSet.MethodSet = new HashSet<String>();

		/*Get the dictionary of container interfaces*/
		jtp.add(new Transform("jtp.methodDisplayer", new InvokeMethodDisplayer()));
		sootArg[0] = "-process-dir";
		sootArg[1] = "../mySootPass/Sample/container";
		soot.Main.main(sootArg);

		/*output the method set*/
		for (String sm : MethodSet.MethodSet) {
			System.out.println(sm);
		}
		MethodSet.printToFile();
	}
}