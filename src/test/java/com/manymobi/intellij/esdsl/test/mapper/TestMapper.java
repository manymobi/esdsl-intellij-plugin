package com.manymobi.intellij.esdsl.test.mapper;

import com.manymobi.esdsl.annotations.Mapper;
import com.manymobi.esdsl.annotations.Param;
import com.manymobi.esdsl.annotations.Query;
import com.manymobi.esdsl.annotations.RequestBody;
import com.manymobi.esdsl.annotations.RequestMapping;
import com.manymobi.esdsl.annotations.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author 梁建军
 * 创建日期： 2021/4/18
 * 创建时间： 上午 11:28
 * @version 1.0
 * @since 1.0
 */
@Mapper("esdsl/test.esdsl")
public interface TestMapper {

    Map search(@Param("content") String content);

    Map search1(@Param("sssss") String sssss, @Param("list") List list);

    @Query("{\"ssss\":#{sssss},\"integer\":#{integer}}")
    @RequestMapping(method = RequestMethod.GET, value = "/v")
    Map search2(@Param("sssss") String sssss, @Param("integer") Integer integer);

    @RequestMapping(method = RequestMethod.GET, value = "/v")
    Map search3(@RequestBody String sssss);

}
