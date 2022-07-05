package com.manymobi.intellij.esdsl.formatting;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author 梁建军
 * 创建日期： 2021/5/27
 * 创建时间： 下午4:28
 * @version 1.0
 * @since 1.0
 */
public class OtherJsonBlock extends EsdslJsonBlock {

    protected OtherJsonBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, CodeStyleSettings settings, SpacingBuilder spacingBuilder, EsdslBlockFactory esdslBlockFactory) {
        super(node, wrap, alignment, settings, spacingBuilder, esdslBlockFactory);
    }
}
