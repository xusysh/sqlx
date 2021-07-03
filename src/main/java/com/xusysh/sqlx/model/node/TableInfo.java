package com.xusysh.sqlx.model.node;

import lombok.Data;

@Data
public class TableInfo {

    String schemaName;

    String name;

    String isView;

    String comment;

}
