parser grammar MigrationsParser;
options { tokenVocab=MigrationsLexer; }

migration_type: ( OP_SCHEMA | OP_TOPIC );
operation: ( FUNCTION_DOWN | FUNCTION_UP );
op_body: FUNCTION_OPEN_CODE_BLOCK BODY FUNCTION_CLOSE_CODE_BLOCK;
function: FUNCTION_DEF operation op_body;

