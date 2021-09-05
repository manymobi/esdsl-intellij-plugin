package com.manymobi.jetbrains.esdsl.test.mapper;

import com.manymobi.esdsl.annotations.Mapper;
import com.manymobi.esdsl.annotations.Param;

import java.util.Map;

/**
 * @author 梁建军
 * 创建日期： 2021/4/18
 * 创建时间： 上午 11:28
 * @version 1.0
 * @since 1.0
 */
@Mapper("esdsl/test2.esdsl")
public interface Test2Mapper {

    Map search(@Param Bean content);

    Map search1(@Param("content") String content, @Param("fulltext") String fulltext);

    Map search2(@Param("content") String content, @Param("fulltext") String fulltext);

    Map search3(@Param Bean content, @Param("fulltext") String fulltext);


    class Bean {
        String key;

        String content;


        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
