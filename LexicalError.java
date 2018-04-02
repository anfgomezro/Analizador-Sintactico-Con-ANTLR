
import javax.swing.JOptionPane;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class LexicalError extends ErrorHandling{

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		
			//System.err.println("\nlexical Error. Line " + line + ":" + charPositionInLine + ", " + msg);
			System.err.println("\n>>> Error lexico(linea:"+line+','+"posicion:"+(charPositionInLine+1)+")");
	
	}
}