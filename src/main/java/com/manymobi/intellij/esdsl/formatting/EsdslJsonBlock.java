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
 * 创建日期： 2021/5/27
 * 创建时间： 下午4:28
 * @version 1.0
 * @since 1.0
 */
public class EsdslJsonBlock extends AbstractBlock {

    protected final SpacingBuilder spacingBuilder;

    protected final CodeStyleSettings settings;

    protected final EsdslBlockFactory esdslBlockFactory;

    protected int spacesStart = 0;

    protected EsdslJsonBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
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
                blocks.add(createBlock(child));
            }
            child = child.getTreeNext();
        }
        return blocks;
    }


    protected Block createBlock(ASTNode astNode) {
        return esdslBlockFactory.createBlockJson(astNode);
    }

    @Override
    public Indent getIndent() {
//        return Indent.getIndent(Indent.Type.NONE, spacesStart * 4, false, false);
        return Indent.getAbsoluteNoneIndent();
//        return null;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return Spacing.createDependentLFSpacing(spacesStart * 4, spacesStart * 4, getTextRange(), false, 0);
//        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    public int getSpacesStart() {
        return spacesStart;
    }

    public void setSpacesStart(int spacesStart) {
        this.spacesStart = spacesStart;
    }
}
