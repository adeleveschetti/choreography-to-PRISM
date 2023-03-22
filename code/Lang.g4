grammar Lang;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

protocol : (preamble)? (varDef)? SEMICOLON (roleDef)+ (protocolID ASSIGN statement);

preamble : PREAMBLE (variableDecl)* ENDPREAMBLE ;

statement :  role FROM role (COMMA role)* COLON (SLPAR rate SRPAR)? (actions FROM)? message DOT statement 
			| role FROM role (COMMA role)* LPAR BRANCH SLPAR rateValues+=rate SRPAR (actions FROM)? message COLON statement (BRANCH SLPAR rateValues+=rate SRPAR (actions FROM)? message COLON statement)* RPAR 
			| (SLPAR rate SRPAR)? internalAction (DOT statement)?
			| ifThenElse 
			| protocolID
			| ifElse
			| END ;

ifThenElse : IF cond AT role THEN CLPAR thenStat=statement CRPAR (ELSE CLPAR elseStat=statement CRPAR)*  ;

ifElse : IFplus LPAR condIf RPAR CLPAR thenStat=statement CRPAR (ELSEplus CLPAR elseStat=statement CRPAR)*  ;

internalAction : CLPAR DOUBLE_STRING CRPAR AT role ; 

protocolID : ID ;

rate : DOUBLE_STRING ;

message : actions (forLoop)* ;

varDef : CHAR EQ INTEGER;

actions : (action+=DOUBLE_STRING)? (AND action+=DOUBLE_STRING)*  ;

roleDef : role FROM (indexSpec)? roleSpec* SEMICOLON;

roleSpec : (role (COLON (roleVar (COMMA roleVar)*))?);

roleGroup : ID  ;

roleIndex : ID SLPAR (index | (index BRANCH INTEGER)) SRPAR ;

indexSpec : index IN SLPAR INTEGER DOTS upperBound=CHAR SRPAR ; 

role : (roleGroup | roleIndex) ;

forLoop : FOREACH LPAR indexIteration=index op=(EQ | LE | GE | LEQ | GEQ | NEQ ) upperBound=index RPAR actions AT role;

roleVar : DOUBLE_STRING;

variableDecl : DOUBLE_STRING;

cond : DOUBLE_STRING ; 

condIf : index op=(EQ | LE | GE | LEQ | GEQ | NEQ ) (INTEGER | ID) ; 

index : CHAR ;

id : ID ;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
SEMICOLON   : ';' ;
COLON 		: ':' ;
DOT 		: '.' ;
DOTS 		: '...' ;
COMMA		: ',' ;
BRANCH 		: '+' ;
FROM 		: '->';
ASSIGN 		: ':=' ;
NEQ 		: '!=' ;
EQ 			: '=' ;
LEQ			: '<=';
GEQ			: '>=';
LE			: '<';
GE			: '>';
UNDERSCORE 	: '_' ;
STAR 		: '*' ;
LPAR   		: '(' ;
RPAR   		: ')' ;
SLPAR   	: '[' ;
SRPAR   	: ']' ;
CLPAR  		: '{' ;
CRPAR  		: '}' ;
AT 			: '@' ;
IF 			: 'if';
THEN		: 'then';
ELSE		: 'else';
IFplus  	: 'IF' ;
ELSEplus	: 'ELSE';
END 		: 'END';
AND			: '&&' ;
PREAMBLE	: 'preamble';
ENDPREAMBLE : 'endpreamble';
CONST 		: 'const';
FOREACH		: 'foreach';
IN			: 'in' ;
WS  		: [ \t\r\n\u00a0]+ -> skip ;
DOUBLE_STRING
    : '"' ~('"')+ '"'
    ;
    
//Numbers
fragment DIGIT : '0'..'9';    
INTEGER       : DIGIT+;

//IDs
CHAR  : 'a'..'z' |'A'..'Z' ;
ID              :  (CHAR | UNDERSCORE)+ (CHAR | DIGIT | UNDERSCORE | STAR)* CHAR*;

