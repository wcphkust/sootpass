package instrumenter;
import soot.*;
import soot.options.Options;
import java.io.File;

/*
Reference:
		add phases to transformer pack by call Pack.add
		Scene Transformer: https://www.sable.mcgill.ca/soot/doc/soot/SceneTransformer.html
		Body Transformer: https://www.sable.mcgill.ca/soot/doc/soot/BodyTransformer.html
 */
public class InstrumenterEntry {
	public static void main(String[] args) {
		String[] sootArg = new String[2];

		Options.v().set_soot_classpath(Scene.v().defaultClassPath() + File.pathSeparator+"../mySootPass/Profiler/bin/");
		Pack jtp = PackManager.v().getPack("jtp");

		/*Get the dictionary of container interfaces*/
		jtp.add(new Transform("jtp.staticInstrumenter", new InvokeStaticInstrumenter()));
		sootArg[0] = "-process-path";
		sootArg[1] = ""; //add path
		Main.main(sootArg);
	}
}