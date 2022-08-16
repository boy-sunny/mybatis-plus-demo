package com.example.mybatisplusdemo.extend.intercepter;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.example.mybatisplusdemo.extend.annotation.DataPermissions;
import com.example.mybatisplusdemo.extend.provider.DataRuler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 基于MyBatis-Plus插件主体的 数据权限扩展插件
 *
 * @author Matrix
 * @date 2022/8/15 10:43
 */
@Slf4j
public class DataPermissionsInnerInterceptor extends BaseInnerInterceptor {

    private final DataRuler dataRuler = SpringUtil.getBean(DataRuler.class);

    // private static final Cache<String, Resolver> CACHE_CONTAINER = Caffeine.newBuilder()
    //         .maximumSize(10000)
    //         .expireAfterWrite(Duration.ofMillis(1))
    //         .refreshAfterWrite(Duration.ofMinutes(30))
    //         .build();

    private static final Map<String, Resolver> CACHE_CONTAINER = new HashMap<>();

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
        log.warn("DataPermissionsInnerInterceptor beforeQuery");

        Resolver resolving = getResolver(ms);
        Method method = resolving.getMethod();
        DataPermissions dataPermissions = method.getAnnotation(DataPermissions.class);
        String expression = dataPermissions.expression();
        Assert.notNull(expression, "自定义的表达式 没有被捕捉到");

        log.warn("获取的数据规则: {}", dataRuler.get());
    }

    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) {
        log.warn("DataPermissionsInnerInterceptor willDoUpdate");
        return super.willDoUpdate(executor, ms, parameter);
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        super.beforeUpdate(executor, ms, parameter);
        log.warn("DataPermissionsInnerInterceptor beforeUpdate");
    }

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        super.beforePrepare(sh, connection, transactionTimeout);
        log.warn("DataPermissionsInnerInterceptor beforePrepare");

        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        MappedStatement ms = mpSh.mappedStatement();
        SqlCommandType sct = ms.getSqlCommandType();
        Assert.notNull(sct, "SqlCommandType获取失败");
    }

    @Override
    public void beforeGetBoundSql(StatementHandler sh) {
        super.beforeGetBoundSql(sh);
        log.warn("DataPermissionsInnerInterceptor beforeGetBoundSql");
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        log.warn("DataPermissionsInnerInterceptor setProperties");
    }

    @Data
    @AllArgsConstructor
    protected static class Resolver {
        private Class<?> objClass;
        private Method method;
    }

    protected Resolver getResolver(MappedStatement ms) {
        String key = ms.getId();
        return CACHE_CONTAINER.getOrDefault(key, resolving(key));
    }

    private Resolver resolving(String key) {
        Resolver resolver = null;
        int split = key.lastIndexOf(".");
        String className = key.substring(0, split);
        String methodName = key.substring(split + 1);
        try {
            Class<?> objClass = Class.forName(className);
            Method method = ReflectUtil.getMethodByName(objClass, methodName);
            resolver = new Resolver(objClass, method);
        } catch (ClassNotFoundException e) {
            log.error("解析类异常: ", e);
        }

        CACHE_CONTAINER.put(key, resolver);
        return resolver;
    }
}
