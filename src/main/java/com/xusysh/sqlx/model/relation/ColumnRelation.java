package com.xusysh.sqlx.model.relation;

import lombok.Data;

import java.util.List;

@Data
public class ColumnRelation {

    String originTable;

    String originName;

    String targetTable;

    String targetName;

    String process;

}
