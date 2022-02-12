package com.xusysh.sqlx.parser.ast.plsql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public abstract class PlSqlLexerBase extends Lexer
{
    public PlSqlLexerBase(CharStream input)
	{
		super(input);
    }

    protected boolean IsNewlineAtPos(int pos)
    {
        int la = _input.LA(pos);
        return la == -1 || la == '\n';
    }
}
