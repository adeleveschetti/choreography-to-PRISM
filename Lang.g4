grammar Lang;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

protocol : protocolID COMMA (role (COMMA role)+) ASSIGN statement;

statement : role FROM role COLON message DOT statement 
			| role FROM role LPAR BRANCH SLPAR rateValues+=rate SRPAR message COLON statement (BRANCH SLPAR rateValues+=rate SRPAR message COLON statement)+ RPAR 
			| internalAction (DOT statement)?
			| ifThenElse 
			| protocolID
			| END ;

ifThenElse : IF cond AT role THEN CLPAR thenStat=statement CRPAR (ELSE CLPAR elseStat=statement CRPAR)*  ;

internalAction : CLPAR DOUBLE_STRING CRPAR AT role ; 

protocolID : ID ;

rate : ID ;

message : DOUBLE_STRING ;

role : ID ;

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

