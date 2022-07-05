package com.manymobi.intellij.esdsl;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.manymobi.esdsl.antlr4.EsdslLexer;
import com.manymobi.esdsl.antlr4.EsdslParser;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.parser.SyntaxErrorListener;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * A highlighter is really just a mapping from token type to
 * some text attributes using {@link #getTokenHighlights(IElementType)}.
 * The reason that it returns an array, TextAttributesKey[], is
 * that you might want to mix the attributes of a few known highlighters.
 * A {@link TextAttributesKey} is just a name for that a theme
 * or IDE skin can set. For example, {@link com.intellij.openapi.editor.DefaultLanguageHighlighterColors#KEYWORD}
 * is the key that maps to what identifiers look like in the editor.
 * To change it, see dialog: Editor > Colors & Fonts > Language Defaults.
 * <p>
 * From <a href="http://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.html">doc</a>:
 * "The mapping of the TextAttributesKey to specific attributes used
 * in an editor is defined by the EditorColorsScheme class, and can
 * be configured by the user if the plugin provides an appropriate
 * configuration interface.
 * ...
 * The syntax highlighter returns the {@link TextAttributesKey}
 * instances for each token type which needs special highlighting.
 * For highlighting lexer errors, the standard TextAttributesKey
 * for bad characters HighlighterColors.BAD_CHARACTER can be used."
 * 语法颜色
 */
public class EsdslSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    public static final TextAttributesKey ID =
            createTextAttributesKey("SAMPLE_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey PARAMETER =
            createTextAttributesKey("PARAMETER", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey FUNCTION_DECLARATION =
            createTextAttributesKey("FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("SAMPLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("SAMPLE_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("SAMPLE_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("SAMPLE_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(EsdslLanguage.INSTANCE,
                EsdslParser.tokenNames,
                EsdslParser.ruleNames);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        EsdslLexer lexer = new EsdslLexer(null);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new SyntaxErrorListener()); // trap errors
        return new ANTLRLexerAdaptor(EsdslLanguage.INSTANCE, lexer);
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (!(tokenType instanceof TokenIElementType)) return EMPTY_KEYS;
        TokenIElementType myType = (TokenIElementType) tokenType;
        int ttype = myType.getANTLRTokenType();
        TextAttributesKey attrKey;
        switch (ttype) {
            case EsdslLexer.ID:
                attrKey = ID;
                break;
            case EsdslLexer.FUNC:
                attrKey = FUNCTION_DECLARATION;
                break;
            case EsdslLexer.AND_OR_XOR:
            case EsdslLexer.REQUEST_METHOD:
            case EsdslLexer.IF:
            case EsdslLexer.ELSE:
            case EsdslLexer.ELSEIF:
            case EsdslLexer.FOR:
            case EsdslLexer.ENDIF:
            case EsdslLexer.ENDFOR:
            case EsdslLexer.OPEN:
            case EsdslLexer.CLOSE:
            case EsdslLexer.SEPARATOR:
            case EsdslLexer.IN:
            case EsdslLexer.AND:
            case EsdslLexer.WRONG:
            case EsdslLexer.LOGIC_CHARACTER:
            case EsdslLexer.EQUAL:
            case EsdslLexer.QUESTION_MARK:
                attrKey = KEYWORD;
                break;

            case EsdslLexer.PARAMETER:
                attrKey = PARAMETER;
                break;
            case EsdslLexer.STRING:
                attrKey = STRING;
                break;
            case EsdslLexer.BLOCK_COMMENT:
                attrKey = LINE_COMMENT;
                break;
            case EsdslLexer.LINE_COMMENT:
                attrKey = BLOCK_COMMENT;
                break;
            case EsdslLexer.NUMBER:
                attrKey = NUMBER;
                break;
            case EsdslLexer.SYMBOL:
            case EsdslLexer.SLASH:
            case EsdslLexer.LPAREN:
            case EsdslLexer.RPAREN:
            case EsdslLexer.COLON:
            case EsdslLexer.COMMA:
            case EsdslLexer.LBRACK:
            case EsdslLexer.RBRACK:
            case EsdslLexer.LBRACE:
            case EsdslLexer.RBRACE:
            case EsdslLexer.TRUE:
            case EsdslLexer.FALSE:
            case EsdslLexer.NULL:
            case EsdslLexer.SINGLE_QUOTATION_MARK:
            case EsdslLexer.LINE_SKIPPING:
            case EsdslLexer.WS:
            default:
                return EMPTY_KEYS;
        }
        return new TextAttributesKey[]{attrKey};
    }
}
