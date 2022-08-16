package com.example.mybatisplusdemo.extend.provider.impl;

import com.example.mybatisplusdemo.extend.provider.DataRuler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 默认规则提供器
 *
 * @author Matrix
 * @date 2022/8/15 11:16
 */
@Slf4j
public class DataRulerDefaultProvider implements DataRuler {

    @Override
    public Map<String, String> get() {
        log.info("DataRulerDefaultProvider");
        return null;
    }
}
