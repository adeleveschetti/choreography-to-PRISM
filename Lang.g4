grammar Lang;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

protocol : protocolID=id ASSIGN statement;

statement : role FROM role COLON message DOT statement 
			| role FROM role LPAR message COLON statement (BRANCH message COLON statement)+ RPAR DOT statement 
			| internalAction 
			| protocolID=id 
			| END ;

internalAction : CLPAR SINGLE_STRING CRPAR role ; //TO DO

message : ID ;

role : ID ;

id : ID ;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
SEMICOLON   : ';' ;
COLON 		: ':' ;
DOT 		: '.' ;
BRANCH 		: '+' ;
FROM 		: '->';
ASSIGN 		: ':=' ;
UNDERSCORE 	: '_' ;
LPAR   		: '(' ;
RPAR   		: ')' ;
CLPAR  		: '{' ;
CRPAR  		: '}' ;
END 		: 'end';
WS  		: [ \t\r\n\u00a0]+ -> skip ;
SINGLE_STRING
    : '\'' ~('\'')+ '\''
    ;
    
//Numbers
fragment DIGIT : '0'..'9';    
INTEGER       : DIGIT+;

//IDs
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              :  (CHAR | UNDERSCORE)+ (CHAR | DIGIT | UNDERSCORE)* CHAR*;

