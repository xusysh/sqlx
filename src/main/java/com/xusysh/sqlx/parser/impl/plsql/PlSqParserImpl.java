package com.xusysh.sqlx.parser.impl.plsql;

import com.xusysh.sqlx.model.node.TableInfo;
import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.parser.ast.plsql.PlSqlLexer;
import com.xusysh.sqlx.parser.ast.plsql.PlSqlParser;
import com.xusysh.sqlx.parser.ast.plsql.PlSqlParserBaseVisitor;
import lombok.Data;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

@Data
public class PlSqParserImpl extends PlSqlParserBaseVisitor implements SqlParser {

    Map<String, TableInfo> tables;

    Map<String, Object> relations;

    @Override
    public boolean parse(String sqlContent) {
        CharStream script = CharStreams.fromString(sqlContent);
        PlSqlLexer lexer = new PlSqlLexer(script);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PlSqlParser parser = new PlSqlParser(tokens);
        ParseTree tree = parser.sql_script();
        this.visit(tree);
        System.out.println("parse succeed");
        return true;
    }

    @Override
    public Object visitSql_script(PlSqlParser.Sql_scriptContext ctx) {
        System.out.println("sql script");
        return super.visitSql_script(ctx);
    }

    @Override
    public Object visitCreate_table(PlSqlParser.Create_tableContext ctx) {
        ctx.tableview_name().getText();
        System.out.println("create table");
        return super.visitCreate_table(ctx);
    }
}
