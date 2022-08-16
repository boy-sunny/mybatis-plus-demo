package com.example.mybatisplusdemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.example.mybatisplusdemo.extend.example.SqlMonitorInnerInterceptor;
import com.example.mybatisplusdemo.extend.intercepter.DataPermissionsInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Matrix
 * @date 2022/8/15 10:28
 */
@Configuration
@MapperScan("com.example.mybatisplusdemo.mapper")
public class MybatisPlusConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 数据权限 + 自定义扩展
        interceptor.addInnerInterceptor(new SqlMonitorInnerInterceptor(new DataPermissionsInnerInterceptor()));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
