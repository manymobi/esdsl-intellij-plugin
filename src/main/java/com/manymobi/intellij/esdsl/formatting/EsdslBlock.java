package com.manymobi.intellij.esdsl.formatting;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2021/5/26
 * 创建时间： 下午8:14
 * @version 1.0
 * @since 1.0
 */
public class EsdslBlock extends AbstractBlock {

    protected final SpacingBuilder spacingBuilder;

    protected final CodeStyleSettings settings;

    protected final EsdslBlockFactory esdslBlockFactory;

    protected EsdslBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                         CodeStyleSettings settings, SpacingBuilder spacingBuilder,
                         EsdslBlockFactory esdslBlockFactory) {
        super(node, wrap, alignment);
        this.settings = settings;
        this.spacingBuilder = spacingBuilder;
        this.esdslBlockFactory = esdslBlockFactory;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = esdslBlockFactory.createBlock(child);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
