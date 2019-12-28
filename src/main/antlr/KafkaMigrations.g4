grammar KafkaMigrations;


migration : OP_LITERAL apply_function revert_function;
apply_function : 'def' 'up' code_block SEMICOLON ;
revert_function : 'def' 'down' code_block SEMICOLON ;

code_block
  : FUNCTION_OPEN_CODE_BLOCK (method | variable)+ FUNCTION_CLOSE_CODE_BLOCK
  ;

variable: 'var' ID '=' '"' ID '"' SEMICOLON;
method: ID '(' params_with_comma last_param ')' SEMICOLON ;
params_with_comma: (param ','  )* ;
last_param: param?;
param: '"' ID '"' | ID;


WS: [ \t\n\r]+ -> skip ;
ID : [a-zA-Z0-9]+ ;

ASSIGN: '=' ;
SEMICOLON: ';' ;

SCHEMA_MIGRATION_LITERAL: 'SchemaMigration';
TOPIC_MIGRATION_LITERAL: 'TopicMigration';
ACCESS_MIGRATION_LITERAL: 'AccessMigration';

OP_LITERAL : ( SCHEMA_MIGRATION_LITERAL | TOPIC_MIGRATION_LITERAL | ACCESS_MIGRATION_LITERAL ) SEMICOLON;

FUNCTION_OPEN_CODE_BLOCK: '{';
FUNCTION_CLOSE_CODE_BLOCK: '}';
