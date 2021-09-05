package com.manymobi.jetbrains.esdsl.formatting;

import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

/**
 * @author 梁建军
 * 创建日期： 2021/5/26
 * 创建时间： 下午8:09
 * @version 1.0
 * @since 1.0
 */
public class EsdslFormattingModelBuilder implements FormattingModelBuilder {


    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        return FormattingModelProvider
                .createFormattingModelForPsiFile(element.getContainingFile(),
                        new EsdslBlockFactory(settings).createBlock(element.getNode()),
                        settings);
    }
}
