package com.manymobi.jetbrains.esdsl;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * 在设置页修改颜色
 */
public class EsdslColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
			new AttributesDescriptor("Identifier", EsdslSyntaxHighlighter.ID),
			new AttributesDescriptor("Number", EsdslSyntaxHighlighter.NUMBER),
			new AttributesDescriptor("Parameter", EsdslSyntaxHighlighter.PARAMETER),
			new AttributesDescriptor("FunctionDeclaration", EsdslSyntaxHighlighter.FUNCTION_DECLARATION),
            new AttributesDescriptor("Keyword", EsdslSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("String", EsdslSyntaxHighlighter.STRING),
            new AttributesDescriptor("Line comment", EsdslSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Block comment", EsdslSyntaxHighlighter.BLOCK_COMMENT),
    };

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Icons.SAMPLE_ICON;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new EsdslSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "==> search\n" +
                "GET /index/${fulltext}/_search\n" +
                "{\n" +
                "  \"query\" : {\n" +
                "\t#if(232!=\"rwerwe\")\n" +
                "    \"match\" : {\n" +
                "      \"id\" :123133131223123212313131\n" +
                "      }\n" +
                "\t#endif\n" +
                "  },\n" +
                "   \"highlight\" : {\n" +
                "        \"pre_tags\" : [\"<tag1>\", \"<tag2>\"],\n" +
                "        \"post_tags\" : [\"</tag1>\", \"</tag2>\"],\n" +
                "        \"fields\" : {\n" +
                "            \"content\" : #{content}\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/**\n" +
                " * 注解\n" +
                " */\n" +
                "==> search1\n" +
                "GET /index/fulltext/_search\n" +
                "{\n" +
                "  \"query\" : {\n" +
                "    \"match\" : {\n" +
                "      \"id\" :#{sssss}   // 注解\n" +
                "      }\n" +
                "  },\n" +
                "   \"highlight\" : {\n" +
                "        \"pre_tags\" :\n" +
                "            #for(item in #{list} open='[' close=']' separator=',')\n" +
                "                #{item}\n" +
                "            #endfor\n" +
                "        ,\n" +
                "        \"post_tags\" : [\"</tag1>\", \"</tag2>\"],\n" +
                "        \"fields\" : {\n" +
                "            \"content\" : {}\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Esdsl";
    }
}
