package com.xusysh.sqlx.model.node;

import lombok.Data;

import java.util.List;

@Data
public class TableInfo {

    String schemaName;

    String name;

    String isView;

    String comment;

    List<ColumnInfo> columns;

}
