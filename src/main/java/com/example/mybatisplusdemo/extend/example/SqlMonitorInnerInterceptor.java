package com.example.mybatisplusdemo.extend.example;

import com.example.mybatisplusdemo.extend.intercepter.BaseInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 示例扩展：SQL监听器拦截
 *
 * @author Matrix
 * @date 2022/8/16 15:16
 */
@Slf4j
public class SqlMonitorInnerInterceptor extends BaseInnerInterceptor {
    public SqlMonitorInnerInterceptor(BaseInnerInterceptor baseInterceptor) {
        super(baseInterceptor);
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        log.warn("本次执行的SQL参数: {}", parameter);
    }

}
