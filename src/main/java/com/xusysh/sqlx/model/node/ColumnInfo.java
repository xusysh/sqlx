package com.xusysh.sqlx.model.node;

import lombok.Data;

@Data
public class ColumnInfo {

    String tableName;

    String name;

    String type;

    String nullable;

    String isPk;

    String isIndex;

    String comment;

}
