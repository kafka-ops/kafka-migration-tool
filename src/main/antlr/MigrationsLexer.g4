lexer grammar MigrationsLexer;

WS: [ \t\n]+ -> skip ;

BODY : ( 'a'..'z' )+;

ASSIGN: '=';
OP_SCHEMA: 'schema';
OP_TOPIC: 'topic';

FUNCTION_DEF: 'def';
FUNCTION_UP: 'up';
FUNCTION_DOWN: 'down';

FUNCTION_OPEN_CODE_BLOCK: '{';
FUNCTION_CLOSE_CODE_BLOCK: '}';


