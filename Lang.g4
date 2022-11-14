grammar Lang;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

protocol : (preamble)? (roleDef)+ protocolID ASSIGN statement;

preamble : PREAMBLE (variableDecl)* ENDPREAMBLE ;

statement : role FROM role COLON (SLPAR rate SRPAR)? message DOT statement 
			| role FROM role LPAR BRANCH SLPAR rateValues+=rate SRPAR message COLON statement (BRANCH SLPAR rateValues+=rate SRPAR message COLON statement)+ RPAR 
			| internalAction (DOT statement)?
			| ifThenElse 
			| protocolID
			| END ;

ifThenElse : IF cond AT role THEN CLPAR thenStat=statement CRPAR (ELSE CLPAR elseStat=statement CRPAR)*  ;

internalAction : CLPAR DOUBLE_STRING CRPAR AT role ; 

protocolID : ID ;

rate : ID ;

message : actions (forLoop)? ;

actions : DOUBLE_STRING ;

roleDef : roleGroup FROM roleSpec+;

roleSpec : (role (COLON (roleVar (COMMA roleVar)+))? SEMICOLON);

roleGroup : ID ;

role : ID ;

forLoop : FOREACH LPAR role NEQ role RPAR actions AT role;

roleVar : DOUBLE_STRING;

variableDecl : DOUBLE_STRING;

cond : DOUBLE_STRING ; 

id : ID ;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
SEMICOLON   : ';' ;
COLON 		: ':' ;
DOT 		: '.' ;
COMMA		: ',' ;
BRANCH 		: '+' ;
FROM 		: '->';
ASSIGN 		: ':=' ;
NEQ 		: '!=' ;
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
END 		: 'END';
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
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              :  (CHAR | UNDERSCORE)+ (CHAR | DIGIT | UNDERSCORE | STAR)* CHAR*;

