package com.manymobi.jetbrains.esdsl;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

/**
 * @author 梁建军
 * 创建日期： 2021/5/26
 * 创建时间： 上午11:59
 * @version 1.0
 * @since 1.0
 */
public class EsdslCommenter implements Commenter {
    @Override
    public @Nullable String getLineCommentPrefix() {
        return "//";
    }

    @Override
    public @Nullable String getBlockCommentPrefix() {
        return "/*";
    }

    @Override
    public @Nullable String getBlockCommentSuffix() {
        return "*/";
    }

    @Override
    public @Nullable String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Override
    public @Nullable String getCommentedBlockCommentSuffix() {
        return null;
    }
}
