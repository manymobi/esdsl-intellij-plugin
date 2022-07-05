package com.manymobi.intellij.esdsl;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.manymobi.esdsl.antlr4.EsdslParser;
import com.manymobi.intellij.esdsl.psi.FunctionSubtree;
import com.manymobi.intellij.esdsl.psi.IdentifierPSINode;
import com.manymobi.intellij.esdsl.psi.VardefSubtree;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 提供全局搜索
 */
public class SampleFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return null;
    }

    /**
     * Is "find usages" meaningful for a kind of definition subtree?
     */
    @Override
    public boolean canFindUsagesFor(PsiElement psiElement) {
        return psiElement instanceof IdentifierPSINode || // the case where we highlight the ID in def subtree itself
                psiElement instanceof FunctionSubtree ||   // remaining cases are for resolve() results
                psiElement instanceof VardefSubtree;
    }

    @Nullable
    @Override
    public String getHelpId(PsiElement psiElement) {
        return null;
    }

    /**
     * What kind of thing is the ID node? Can group by in "Find Usages" dialog
     */
    @NotNull
    @Override
    public String getType(PsiElement element) {
        // The parent of an ID node will be a RuleIElementType:
        // function, vardef, formal_arg, statement, expr, call_expr, primary
        ANTLRPsiNode parent = (ANTLRPsiNode) element.getParent();
        RuleIElementType elType = (RuleIElementType) parent.getNode().getElementType();
        switch (elType.getRuleIndex()) {
            case EsdslParser.FUNC:
                return "function";
            case EsdslParser.RULE_parameter:
                return "variable";
        }
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(PsiElement element) {
        return element.getText();
    }

    @NotNull
    @Override
    public String getNodeText(PsiElement element, boolean useFullName) {
        String text = element.getText();
        System.out.println(text);
        return text;
    }
}
