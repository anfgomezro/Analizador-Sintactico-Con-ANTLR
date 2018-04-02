import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main
{
	private static TLLexer lexer;

	public static boolean isIdentificador(int tipo){
		if(	lexer.WHILE == tipo ||
		    lexer.FOR == tipo ||
			lexer.IF == tipo ||
			lexer.ELSE == tipo ||
			lexer.LOG == tipo ||
			lexer.FUNCION == tipo ||
			lexer.FALSE == tipo ||
			lexer.TRUE == tipo ||
			lexer.IMPORTAR == tipo ||
			lexer.IN == tipo ||
			lexer.RETORNO == tipo ||
			lexer.END == tipo ||
			lexer.DESDE == tipo ||
			lexer.TODO == tipo
			)
				return true;
		return false;
	}

	public static boolean isOperador(int tipo){
		if( lexer.TOKEN_LLAVE_IZQ==tipo ||
			lexer.TOKEN_LLAVE_DER==tipo ||
			lexer.TOKEN_COR_IZQ==tipo ||
			lexer.TOKEN_COR_DER==tipo ||
			lexer.TOKEN_PAR_IZQ==tipo ||
			lexer.TOKEN_PAR_DER==tipo ||
			lexer.TOKEN_MAYOR==tipo ||
			lexer.TOKEN_MENOR==tipo ||
			lexer.TOKEN_MAYOR_IGUAL==tipo ||
			lexer.TOKEN_MENOR_IGUAL==tipo ||
			lexer.TOKEN_IGUAL_NUM==tipo ||
			lexer.TOKEN_POINT==tipo ||
			lexer.TOKEN_DIFF_NUM==tipo ||
			lexer.TOKEN_AND==tipo ||
			lexer.TOKEN_OR==tipo ||
			lexer.TOKEN_NOT==tipo ||
			lexer.TOKEN_MAS==tipo ||
			lexer.TOKEN_MENOS==tipo ||
			lexer.TOKEN_MUL==tipo ||
			lexer.TOKEN_DIV==tipo ||
			lexer.TOKEN_MOD==tipo ||
			lexer.TOKEN_POT==tipo ||
			lexer.TOKEN_ASSIGN==tipo ||
			lexer.TOKEN_COMA==tipo ||
			lexer.TOKEN_DOSP==tipo
			)
				return true;
		return false;
	}

	public static void printOutput(Token token, String tipo_de_token, String lexema, int fila, int columna) {
		if(lexer.TOKEN_STRING == token.getType()) {
			lexema = lexema.substring(1, lexema.length()-1);
		}
		if (isIdentificador(token.getType()) || isOperador(token.getType())) {
			System.out.println("<" + tipo_de_token + "," + String.valueOf(fila) + "," + String.valueOf(columna) + ">");
		} else {
			System.out.println("<" + tipo_de_token + "," + lexema + "," + String.valueOf(fila) + "," + String.valueOf(columna) + ">");
		}
	}

	public static void main( String[] args) throws Exception
	{
		if (args.length>0)
			lexer = new TLLexer(CharStreams.fromFileName(args[0]));
		else
			lexer = new TLLexer(CharStreams.fromStream(System.in));

		lexer.removeErrorListeners();
		lexer.addErrorListener(new LexicalError());

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println("El analisis sintactico ha finalizado correctamente.");
		for (Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer.nextToken()) {

			printOutput(
					token,
					lexer.getRuleNames()[token.getType()-1].toLowerCase(),
					token.getText(),
					token.getLine(),
					token.getCharPositionInLine()+1);
		}
	}
}
