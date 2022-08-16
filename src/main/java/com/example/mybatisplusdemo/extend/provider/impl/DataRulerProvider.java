package com.example.mybatisplusdemo.extend.provider.impl;

import com.example.mybatisplusdemo.extend.provider.DataRuler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据规则提供类
 *
 * @author Matrix
 * @date 2022/8/15 11:02
 */
@Service
public class DataRulerProvider implements DataRuler {

    @Override
    public Map<String, String> get() {
        Map<String, String> rules = new HashMap<>();
        rules.put(" = ", "dept");
        return rules;
    }
}
