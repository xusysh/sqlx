package com.xusysh.sqlx.parser.impl;

import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.parser.ast.PlSqlParserBaseVisitor;

public class PlSqlParserImpl extends PlSqlParserBaseVisitor implements SqlParser {
    @Override
    public boolean parse(String sqlContent) {
        return true;
    }
}
