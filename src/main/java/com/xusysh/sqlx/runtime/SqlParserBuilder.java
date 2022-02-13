package com.xusysh.sqlx.runtime;

import com.xusysh.sqlx.enums.SqlType;
import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.parser.impl.plsql.PlSqParserImpl;
import com.xusysh.sqlx.parser.impl.postgresql.PostgresqlParserImpl;

public class SqlParserBuilder {

    public static SqlParser getSqlParser(SqlType sqlType) {
        switch (sqlType) {
            case PL_SQL:
                return new PlSqParserImpl();
            case POSTGRE_SQL:
                return new PostgresqlParserImpl();
            default:
                return null;
        }
    }


}
