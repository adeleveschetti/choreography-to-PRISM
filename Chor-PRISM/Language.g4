grammar Language;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

protocol : (preamble)? (varDef)? SEMICOLON (roleDef)+ (protocolID ASSIGN statement)*;

protocolID : ID ;

statement : branch | ifThenElse | rec  ;

branch : inputRole=role FROM outputRole+=role (COMMA outputRole+=role)* COLON (BRANCH? SLPAR rateValues+=rate SRPAR updates DOT statement)* ;

ifThenElse : IF cond AT role THEN CLPAR thenStat=statement CRPAR (ELSE CLPAR elseStat=statement CRPAR)*;

rec : protocolID ;

updates : (SQLPAR (prec=actions) SQRPAR)? upds=message ;

message : actions | first=DOUBLE_STRING AND loop | loop AND second=DOUBLE_STRING | loop*;

actions : (action+=DOUBLE_STRING)? (AND action+=DOUBLE_STRING)*  ;

loop : FOREACH LPAR indexIteration=index op=(EQ | LE | GE | LEQ | GEQ | NEQ ) upperBound=index RPAR DOUBLE_STRING AT role;

preamble : PREAMBLE (variableDecl)* ENDPREAMBLE ;

varDef : CHAR EQ INTEGER;

roleDef : role FROM (indexSpec)? roleSpec* SEMICOLON;

roleSpec : (role (COLON (roleVar (COMMA roleVar)*))?);

roleGroup : ID  ;

roleIndex : ID SLPAR (index | (index BRANCH INTEGER)) SRPAR ;

indexSpec : index IN SLPAR INTEGER DOTS upperBound=CHAR SRPAR ; 

role : (roleGroup | roleIndex) ;

roleVar : DOUBLE_STRING;

variableDecl : DOUBLE_STRING;

rate : DOUBLE_STRING ;

cond : DOUBLE_STRING ; 

index : CHAR ;

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
SQLPAR		: '<<' ;
SQRPAR		: '>>' ;
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