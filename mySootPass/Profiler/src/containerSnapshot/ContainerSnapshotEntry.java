package containerSnapshot;
import soot.*;
import soot.options.Options;
import java.io.File;

/*
Reference:
		add phases to transformer pack by call Pack.add
		Scene Transformer: https://www.sable.mcgill.ca/soot/doc/soot/SceneTransformer.html
		Body Transformer: https://www.sable.mcgill.ca/soot/doc/soot/BodyTransformer.html
 */
public class ContainerSnapshotEntry {
	public static void main(String[] args) {
		String[] sootArg = new String[2];

		Options.v().set_soot_classpath(Scene.v().defaultClassPath() + File.pathSeparator+"../mySootPass/Profiler/bin/");
		Pack jtp = PackManager.v().getPack("jtp");

		/*Profile the container usage in applications*/
		jtp.add(new Transform("jtp.containerSnapshot", new ContainerSnapshot()));
		sootArg[0] = "-process-path";
		sootArg[1] = "../mySootPass/Sample/application/";
		soot.Main.main(sootArg);
	}
}