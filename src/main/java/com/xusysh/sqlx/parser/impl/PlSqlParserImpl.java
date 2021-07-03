package com.xusysh.sqlx.parser.impl;

import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.parser.ast.PlSqlParserBaseVisitor;
import lombok.Data;

import java.util.Map;

@Data
public class PlSqlParserImpl extends PlSqlParserBaseVisitor implements SqlParser {

    Map<String, Object> symbols;

    @Override
    public boolean parse(String sqlContent) {
        return true;
    }



}
