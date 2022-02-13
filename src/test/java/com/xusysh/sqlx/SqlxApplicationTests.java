package com.xusysh.sqlx;

import com.xusysh.sqlx.enums.SqlType;
import com.xusysh.sqlx.parser.SqlParser;
import com.xusysh.sqlx.runtime.SqlParserBuilder;
import org.junit.jupiter.api.Test;

class SqlxApplicationTests {

    @Test
    public void postgresqlTest() {

        SqlParser postgresqlParser = SqlParserBuilder.getSqlParser(SqlType.POSTGRE_SQL);
        postgresqlParser.parse("create table view1.emm as select * from view1.table1 t1 left join view1.table2 t2 on t1.id=t2.id");

    }

}
