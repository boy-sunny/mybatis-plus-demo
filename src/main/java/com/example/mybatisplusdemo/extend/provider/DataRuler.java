package com.example.mybatisplusdemo.extend.provider;

import java.util.Map;

/**
 * 数据规则
 *
 * @author Matrix
 * @date 2022/8/15 11:04
 */
public interface DataRuler {

    /**
     * 获取数据规则，SQL的解析，修改可以借助：{@link com.baomidou.mybatisplus.extension.parser.JsqlParserSupport}
     *
     * @return 数据规则集合，可以是BO类
     */
    Map<String, String> get();

}
