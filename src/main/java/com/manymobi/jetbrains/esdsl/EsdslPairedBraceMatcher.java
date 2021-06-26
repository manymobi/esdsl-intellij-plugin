package com.manymobi.jetbrains.esdsl;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.manymobi.esdsl.antlr4.EsdslLexer;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2021/5/26
 * 创建时间： 下午12:53
 * @version 1.0
 * @since 1.0
 */
public class EsdslPairedBraceMatcher implements PairedBraceMatcher {

    private BracePair[] pairs;

    public EsdslPairedBraceMatcher() {

        List<TokenIElementType> map = PSIElementTypeFactory.getTokenIElementTypes(EsdslLanguage.INSTANCE);


        pairs = new BracePair[]{
                new BracePair(
                        map.get(EsdslLexer.LPAREN),
                        map.get(EsdslLexer.RPAREN),
                        true
                ),
                new BracePair(
                        map.get(EsdslLexer.LBRACE),
                        map.get(EsdslLexer.RBRACE),
                        true
                ),
                new BracePair(
                        map.get(EsdslLexer.LBRACK),
                        map.get(EsdslLexer.RBRACK),
                        true
                ),
                new BracePair(
                        map.get(EsdslLexer.IF),
                        map.get(EsdslLexer.ENDIF),
                        true
                ),
                new BracePair(
                        map.get(EsdslLexer.FOR),
                        map.get(EsdslLexer.ENDFOR),
                        true
                )
        };
    }

    @Override
    @NotNull
    public BracePair[] getPairs() {
        return pairs;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType iElementType, @Nullable IElementType iElementType1) {
        return false;
    }

    @Override
    public int getCodeConstructStart(PsiFile psiFile, int i) {
        return 0;
    }
}
