package com.xusysh.sqlx.parser.impl.postgresql;

import com.xusysh.sqlx.model.node.TableInfo;
import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.parser.ast.postgresql.PostgreSQLLexer;
import com.xusysh.sqlx.parser.ast.postgresql.PostgreSQLParser;
import com.xusysh.sqlx.parser.ast.postgresql.PostgreSQLParserBaseListener;
import lombok.Data;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.*;

@Data
public class PostgresqlParserImpl extends PostgreSQLParserBaseListener implements SqlParser {

    /**
     * stack of schemaName.tableName
     */
    Stack<String> tableStack = new Stack<>();

    /**
     * schemaName.tableName->tableInfo Object
     */
    Map<String, TableInfo> tableInfoMap = new HashMap<>();

    /**
     * A->B,C...
     * which means B and C are derived from A
     */
    Map<String, List<String>> tableRelationMap = new HashMap<>();

    @Override
    public boolean parse(String sqlContent) {
        CharStream script = CharStreams.fromString(sqlContent);
        PostgreSQLLexer lexer = new PostgreSQLLexer(script);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PostgreSQLParser parser = new PostgreSQLParser(tokens);
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        tableStack = new Stack<>();
        walker.walk(this, tree);
        System.out.println("parse succeed");
        return true;
    }

//    @Override
//    public void enterStmt(PostgreSQLParser.StmtContext ctx) {
//        super.enterStmt(ctx);
//    }

//    @Override
//    public void enterEveryRule(ParserRuleContext ctx) {
//        System.out.println("enter rule: " + ctx.getClass().getName());
//        super.enterEveryRule(ctx);
//    }
//
//    @Override
//    public void exitEveryRule(ParserRuleContext ctx) {
//        System.out.println("exit rule: " + ctx.getClass().getName());
//        super.exitEveryRule(ctx);
//    }


    @Override
    public void enterCreateasstmt(PostgreSQLParser.CreateasstmtContext ctx) {
//        String schemaName = ctx.create_as_target().qualified_name().colid().getText();
//        String tableName = ctx.create_as_target().qualified_name().indirection().indirection_el().get(0).attr_name().getText();
        String curTargetName = ctx.create_as_target().qualified_name().getText();
//        tableStack.push(curTargetName);
        System.out.println("enter create as stmt: " + curTargetName);
        super.enterCreateasstmt(ctx);
    }

    @Override
    public void enterQualified_name(PostgreSQLParser.Qualified_nameContext ctx) {
        String curTargetName = ctx.getText();
        tableStack.push(curTargetName);
        super.enterQualified_name(ctx);
    }

    @Override
    public void exitCreateasstmt(PostgreSQLParser.CreateasstmtContext ctx) {
//        String schemaName = ctx.create_as_target().qualified_name().colid().getText();
//        String tableName = ctx.create_as_target().qualified_name().indirection().indirection_el().get(0).attr_name().getText();
        String curTargetName = ctx.create_as_target().qualified_name().getText();
        String schemaTableName = "";
        while (true) {
            schemaTableName = tableStack.pop();
            if (schemaTableName.equals(curTargetName)) break;
            if (tableRelationMap.containsKey(schemaTableName)) {
                tableRelationMap.get(schemaTableName).add(curTargetName);
            } else {
                tableRelationMap.put(schemaTableName, Collections.singletonList(curTargetName));
            }
        }
        System.out.println("exit create as stmt: " + curTargetName);
        super.exitCreateasstmt(ctx);
    }

    @Override
    public void enterCreatestmt(PostgreSQLParser.CreatestmtContext ctx) {
        String tableName = ctx.TABLE().getText();
        System.out.println("enter create table stmt: " + tableName);
        super.enterCreatestmt(ctx);
    }

    @Override
    public void exitCreatestmt(PostgreSQLParser.CreatestmtContext ctx) {
        String tableName = ctx.TABLE().getText();
        System.out.println("leave create table stmt: " + tableName);
        super.exitCreatestmt(ctx);
    }

}