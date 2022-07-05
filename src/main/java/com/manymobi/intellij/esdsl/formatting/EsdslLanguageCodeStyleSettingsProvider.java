package com.manymobi.intellij.esdsl.formatting;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.manymobi.intellij.esdsl.EsdslLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * @author 梁建军
 * 创建日期： 2021/5/26
 * 创建时间： 下午8:25
 * @version 1.0
 * @since 1.0
 */
public class EsdslLanguageCodeStyleSettingsProvider  extends LanguageCodeStyleSettingsProvider {
    @NotNull
    @Override
    public Language getLanguage() {
        return EsdslLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
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
}
