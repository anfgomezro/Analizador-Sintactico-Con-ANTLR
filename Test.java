// import de librerias de runtime de ANTLR
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;

public class Test {
  public static void main(String[] args) throws Exception {
    try{
	// crear un analizador léxico que se alimenta a partir de la entrada (archivo  o consola)
	TLLexer lexer;
	if (args.length>0)
	  lexer = new TLLexer(new ANTLRFileStream(args[0]));
	else
	  lexer = new TLLexer(new ANTLRInputStream(System.in));
	// Identificar al analizador léxico como fuente de tokens para el sintactico
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	// Crear el analizador sintáctico que se alimenta a partir del buffer de tokens
	TLParser parser = new TLParser(tokens);
  
	ParseTree tree = parser.first(); // comienza el análisis en la regla inicial
	System.out.println(tree.toStringTree(parser)); // imprime el árbol en forma textual
    } catch (Exception e){
	System.err.println("Error (Test): " + e);
    }
  }
}
