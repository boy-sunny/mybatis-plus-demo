package com.example.mybatisplusdemo.config;

import com.example.mybatisplusdemo.extend.provider.DataRuler;
import com.example.mybatisplusdemo.extend.provider.impl.DataRulerDefaultProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Matrix
 * @date 2022/8/15 11:20
 */
@Configuration
public class DataRulerBeanConfig {

    @Bean(name = "defaultDataRuler")
    @ConditionalOnMissingBean(DataRuler.class)
    public DataRuler dataRuler() {
       return new DataRulerDefaultProvider();
    }

}
