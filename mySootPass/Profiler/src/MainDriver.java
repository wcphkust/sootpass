/* Usage: java MainDriver [soot-options] appClass
 */


/* import necessary soot packages */
import soot.*;
import soot.options.Options;
import java.io.File;
import instrumenter.InvokeStaticInstrumenter;
import methodDisplayer.InvokeMethodDisplayer;


public class MainDriver {
	public static void setup(String[] args) {
		/* check the arguments */
		if (args.length == 0) {
			System.err.println("Usage: java MainDriver [options] pass name");
			System.exit(0);
		}

		/*Set the soot-classpath to include the helper class and class to analyze*/
		Options.v().set_soot_classpath(Scene.v().defaultClassPath() + File.pathSeparator+"../mySootPass/Profiler/bin/" + File.pathSeparator+"../mySootPass/Sample/src/");
	}


	public static void main(String[] args) {
		setup(args);

		/* add phases to transformer pack by call Pack.add */
		/*
		Scene Transformer: https://www.sable.mcgill.ca/soot/doc/soot/SceneTransformer.html
		Body Transformer: https://www.sable.mcgill.ca/soot/doc/soot/BodyTransformer.html
		 */
		Pack jtp = PackManager.v().getPack("jtp");
		//jtp.add(new Transform("jtp.instrumenter", new InvokeStaticInstrumenter()));
		jtp.add(new Transform("jtp.displayer", new InvokeMethodDisplayer()));


		soot.Main.main(args);
	}
}