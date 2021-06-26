package com.manymobi.jetbrains.esdsl.formatting;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.manymobi.esdsl.antlr4.EsdslLexer;
import com.manymobi.esdsl.antlr4.EsdslParser;
import com.manymobi.jetbrains.esdsl.EsdslLanguage;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2021/5/27
 * 创建时间： 下午2:36
 * @version 1.0
 * @since 1.0
 */
public class EsdslBlockFactory {

    private final CodeStyleSettings settings;

    private final SpacingBuilder spaceBuilder;
    private final SpacingBuilder spaceBuilderJson;

    public EsdslBlockFactory(CodeStyleSettings settings) {
        this.settings = settings;
        spaceBuilder = createSpaceBuilder(settings);
        spaceBuilderJson = createSpaceBuilderJson(settings);
    }

    public AbstractBlock createBlock(@NotNull ASTNode astNode) {
        IElementType elementType = astNode.getElementType();
        List<RuleIElementType> ruleIElementTypes = PSIElementTypeFactory.getRuleIElementTypes(EsdslLanguage.INSTANCE);
        if (ruleIElementTypes.get(EsdslParser.RULE_json) == elementType) {
            return new EsdslJsonBlock(astNode,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    settings,
                    spaceBuilderJson,
                    this);
        }

        return new EsdslBlock(astNode,
                Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment(),
                settings,
                spaceBuilder,
                this);
    }


    public AbstractBlock createBlockJson(@NotNull ASTNode astNode) {
        IElementType elementType = astNode.getElementType();
        List<RuleIElementType> ruleIElementTypes = PSIElementTypeFactory.getRuleIElementTypes(EsdslLanguage.INSTANCE);
        if (ruleIElementTypes.get(EsdslParser.RULE_obj) == elementType) {
            return new ObjJsonBlock(astNode,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    settings,
                    spaceBuilderJson,
                    this);
        }
        if (ruleIElementTypes.get(EsdslParser.RULE_array) == elementType) {
            return new ListJsonBlock(astNode,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    settings,
                    spaceBuilderJson,
                    this);
        }


        List<TokenIElementType> tokenIElementTypes = PSIElementTypeFactory.getTokenIElementTypes(EsdslLanguage.INSTANCE);

        if (tokenIElementTypes.get(EsdslLexer.LBRACE) == elementType||tokenIElementTypes.get(EsdslLexer.RBRACE) == elementType) {
            return new BraceJsonBlock(astNode,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    settings,
                    spaceBuilderJson,
                    this);
        }
        return new OtherJsonBlock(astNode,
                Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment(),
                settings,
                spaceBuilderJson,
                this);
    }


    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        List<TokenIElementType> map = PSIElementTypeFactory.getTokenIElementTypes(EsdslLanguage.INSTANCE);
        return new SpacingBuilder(settings, EsdslLanguage.INSTANCE)
                .after(map.get(EsdslLexer.FUNC))
                .spaces(1)
                .after(map.get(EsdslLexer.REQUEST_METHOD))
                .spaces(1)
                .after(map.get(EsdslLexer.ID))
                .lineBreakInCode()
                .before(map.get(EsdslLexer.EQUAL))
                .none();
    }


    private static SpacingBuilder createSpaceBuilderJson(CodeStyleSettings settings) {

        List<TokenIElementType> map = PSIElementTypeFactory.getTokenIElementTypes(EsdslLanguage.INSTANCE);
        List<RuleIElementType> ruleIElementTypes = PSIElementTypeFactory.getRuleIElementTypes(EsdslLanguage.INSTANCE);
        return new SpacingBuilder(settings, EsdslLanguage.INSTANCE)
                .before(map.get(EsdslLexer.IF))
                .lineBreakInCode()
                .before(ruleIElementTypes.get(EsdslParser.RULE_ifThenStatement))
                .lineBreakOrForceSpace(true, true)
                .before(map.get(EsdslLexer.ENDIF))
                .lineBreakInCode()
                .before(map.get(EsdslLexer.FOR))
                .lineBreakInCode()
                .before(map.get(EsdslLexer.ENDFOR))
                .lineBreakInCode();
    }
}
