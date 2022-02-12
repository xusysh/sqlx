package com.xusysh.sqlx.runtime;

import com.xusysh.sqlx.enums.SqlType;
import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.parser.impl.PlSqlParserImpl;

public class SqlParserRuntime {

    SqlParser sqlParser;

    public static SqlParser getSqlParser(SqlType sqlType) {
        switch (sqlType) {
            case PL_SQL:
                return new PlSqlParserImpl();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        SqlParser sqlParser = getSqlParser(SqlType.PL_SQL);
        sqlParser.parse("CREATE TABLE QQQ AS SELECT * FROM EMM;");
    }

}
