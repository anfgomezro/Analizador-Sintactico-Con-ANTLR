grammar TL;

//Reglas gramaticales

first : (IMPORTAR ID ('.'ID)* | DESDE ID IMPORTAR ID)* (defun)* (stm)*;

defun : FUNCION ID'(' ID (',' ID )* ')' (stm)+  rto END FUNCION;

dec   : ID '=' FALSE
      | ID '=' TRUE
      | ID '=' TOKEN_INTEGER
      | ID '=' TOKEN_FLOAT
      | ID '=' TOKEN_STRING
      | ID '=' ind
      | ID '=' arr
      | ID '=' str
      | ID '=' oper
      ;

rto   : RETORNO'('ID')';

stm   : wrt
      | dec
      | fr
      | conif
      | wh
      | call
      | rd
      ;

wrt   : LOG '(' (ID | oper | TOKEN_INTEGER | TOKEN_FLOAT | boo | ind | (ID'.'ID) | TOKEN_STRING) ')';

oper : ((ID'.'ID) | TOKEN_INTEGER | TOKEN_FLOAT | ind | ID)('+' | '-' | '/'|'*'|'%'|'^'| '||' | '&&' |'<'|'>'|'<='|'>=' | '!' | '!=' | '==')(ID | (ID'.'ID) | TOKEN_INTEGER | TOKEN_FLOAT | oper | ind)
     | '('oper')'
     | oper('+' | '-' | '/' | '*' | '%' | '^' | '||' | '&&' | '<' | '>' | '<=' | '>=' | '!' | '!=' | '==')oper
     ;

boo : (ID | TOKEN_INTEGER | TOKEN_FLOAT | TOKEN_STRING)('||' | '&&' | '<' | '>' | '<=' | '>=' | '!=' | '==')(ID | TOKEN_INTEGER | TOKEN_FLOAT | TOKEN_STRING)
    | '!'(ID | '(' boo ')')
    ;

arr : '['(TOKEN_INTEGER | TOKEN_FLOAT | TOKEN_STRING) (','(TOKEN_INTEGER | TOKEN_FLOAT | TOKEN_STRING))*']';

ind : ID'['TOKEN_INTEGER']';

fr  : FOR ID IN arr '{' stm  '}';

str : '{' ID ':' (TOKEN_FLOAT | TOKEN_INTEGER | TOKEN_STRING) (',' ID ':' (TOKEN_FLOAT | TOKEN_INTEGER | TOKEN_STRING) )* '}';

call : ID'(' (ID | TOKEN_FLOAT | TOKEN_INTEGER)  (','(ID | TOKEN_FLOAT | TOKEN_INTEGER) )* ')';

conif: IF '(' boo ')' '{' stm '}' (ELSE IF '(' boo ')' '{' stm '}' )* (ELSE '{' stm '}')?;

wh  :  WHILE '(' boo ')' '{' stm '}';

rd : LEER '('ID')';


// Define palabras reservadas
WHILE: 'while';
FOR: 'for';
IF: 'if';
ELSE: 'else';
LOG: 'log';
FUNCION: 'funcion';
FALSE: 'false';
TRUE: 'true';
IMPORTAR: 'importar';
IN: 'in';
RETORNO: 'retorno';
END: 'end';
DESDE: 'desde';
TODO: 'todo';
LEER : 'leer';

// Define simbolos y operadores
TOKEN_LLAVE_IZQ: '{';
TOKEN_LLAVE_DER: '}';
TOKEN_COR_IZQ: '[';
TOKEN_COR_DER: ']';
TOKEN_PAR_IZQ: '(';
TOKEN_PAR_DER: ')';
TOKEN_MAYOR: '>';
TOKEN_MENOR: '<';
TOKEN_MAYOR_IGUAL: '>=';
TOKEN_MENOR_IGUAL: '<=';
TOKEN_IGUAL_NUM: '==';
TOKEN_POINT: '.';
TOKEN_DIFF_NUM: '!=';
TOKEN_AND: '&&';
TOKEN_OR: '||';
TOKEN_NOT: '!';
TOKEN_MAS: '+';
TOKEN_MENOS: '-';
TOKEN_MUL: '*';
TOKEN_DIV: '/';
TOKEN_MOD: '%';
TOKEN_POT: '^';
TOKEN_ASSIGN: '=';
TOKEN_COMA: ',';
TOKEN_DOSP: ':';

TOKEN_STRING: '"'.*?'"';
// Otra versi�n TOKEN_STRING         :   '"' ( '\\' ('\\'|'\t'|'\r\n'|'\r'|'\n'|'"') | ~('\\'|'\t'|'\r'|'\n'|'"') )* '"';

COMMENTARIO: '#' .*? '\n' -> skip;
// Otra versi�n	 '#' ~[\r\n]* -> skip;

ID: [a-zA-Z_][a-zA-Z0-9_]*;
TOKEN_INTEGER: [0-9]+;
TOKEN_FLOAT: [0-9]+'.'[0-9]+;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
