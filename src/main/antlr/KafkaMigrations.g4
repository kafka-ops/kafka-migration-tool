grammar KafkaMigrations;


migration : OP_LITERAL apply_function revert_function;
apply_function : 'def' 'up' code_block SEMICOLON ;
revert_function : 'def' 'down' code_block SEMICOLON ;

code_block
  : FUNCTION_OPEN_CODE_BLOCK (method)+ FUNCTION_CLOSE_CODE_BLOCK
  ;

method: ID SEMICOLON ;

WS: [ \t\n\r]+ -> skip ;
ID : [a-zA-Z0-9]+ ;

ASSIGN: '=' ;
SEMICOLON: ';' ;

SCHEMA_MIGRATION_LITERAL: 'SchemaMigration';
TOPIC_MIGRATION_LITERAL: 'TopicMigration';

OP_LITERAL : ( SCHEMA_MIGRATION_LITERAL | TOPIC_MIGRATION_LITERAL ) SEMICOLON;

FUNCTION_OPEN_CODE_BLOCK: '{';
FUNCTION_CLOSE_CODE_BLOCK: '}';
