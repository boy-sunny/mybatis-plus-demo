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
import java.util.*;

/**
 * 基于MyBatis-Plus插件主体的 数据权限扩展插件
 *
 * @author Matrix
 * @date 2022/8/15 10:43
 */
@Slf4j
public class DataPermissionsInnerInterceptor extends BaseInnerInterceptor {

    private final DataRuler dataRuler = SpringUtil.getBean(DataRuler.class);

    private static final Map<String, Resolver> CACHE_CONTAINER = new HashMap<>();

    /**
     * 只处理 select 场景
     *
     * @param executor      Executor(可能是代理对象)
     * @param ms            MappedStatement
     * @param parameter     parameter
     * @param rowBounds     rowBounds
     * @param resultHandler resultHandler
     * @param boundSql      boundSql
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);

        if (!SqlCommandType.SELECT.equals(ms.getSqlCommandType())) {
            return;
        }

        DataPermissions dataPermissions = getDataPermissions(ms);
        if (Objects.nonNull(dataPermissions) && dataPermissions.isClose()) {
            log.warn("== beforeQuery == 不需要数据权限改造SQL !!!!!!!!");
            return;
        }

        log.warn("== beforeQuery == 开启SQL改造，获取的数据规则: {}", dataRuler.get());

    }

    /**
     * 处理 update 和 delete 事件
     *
     * @param executor  Executor(可能是代理对象)
     * @param ms        MappedStatement
     * @param parameter parameter
     */
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        super.beforeUpdate(executor, ms, parameter);

        if (SqlCommandType.UPDATE.equals(ms.getSqlCommandType()) || SqlCommandType.DELETE.equals(ms.getSqlCommandType())) {

            DataPermissions dataPermissions = getDataPermissions(ms);
            if (Objects.nonNull(dataPermissions) && dataPermissions.isClose()) {
                log.warn("== beforeUpdate == 不需要数据权限改造SQL !!!!!!!!");
                return;
            }

            log.warn("== beforeUpdate == 开启SQL改造，获取的数据规则: {}", dataRuler.get());
        }

    }

    /**
     * 获取自定义权限注解信息
     *
     * @param ms MappedStatement
     * @return {@link DataPermissions}
     */
    private DataPermissions getDataPermissions(MappedStatement ms) {
        Resolver resolving = getResolver(ms);
        Method method = resolving.getMethod();
        return method.getAnnotation(DataPermissions.class);
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
