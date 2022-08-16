package com.example.mybatisplusdemo.extend.intercepter;

import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

/**
 * 可扩展的内部SQL拦截器
 *
 * @author Matrix
 * @date 2022/8/16 14:19
 */
public abstract class BaseInnerInterceptor extends JsqlParserSupport implements InnerInterceptor {

    private final Optional<BaseInnerInterceptor> baseInterceptor;

    public BaseInnerInterceptor() {
        baseInterceptor = Optional.empty();
    }

    public BaseInnerInterceptor(BaseInnerInterceptor baseInterceptor) {
        this.baseInterceptor = Optional.ofNullable(baseInterceptor);
    }

    @Override
    public boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return baseInterceptor
                .map(base -> base.willDoQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql))
                .orElse(true);
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        baseInterceptor.ifPresent(base -> base.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql));
    }

    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) {
        return baseInterceptor
                .map(base -> base.willDoUpdate(executor, ms, parameter))
                .orElse(true);
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        baseInterceptor.ifPresent(base -> base.beforeUpdate(executor, ms, parameter));
    }

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        baseInterceptor.ifPresent(base -> base.beforePrepare(sh, connection, transactionTimeout));
    }

    @Override
    public void beforeGetBoundSql(StatementHandler sh) {
        baseInterceptor.ifPresent(base -> base.beforeGetBoundSql(sh));
    }

    @Override
    public void setProperties(Properties properties) {
        baseInterceptor.ifPresent(base -> base.setProperties(properties));
    }

}
