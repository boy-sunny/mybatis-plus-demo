package com.example.mybatisplusdemo.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Matrix
 * @date 2022/8/31 17:57
 */
@Slf4j
@Aspect
@Component
public class AopComponent {

    // @Pointcut("execution(public * com.example.mybatisplusdemo.service.impl.SysUserRoleServiceImpl.saveOrUpdating(..))")
    public void addAdvice(){}

    // @Around(value = "addAdvice()")
    public void arround(ProceedingJoinPoint point){
        try {
            Object proceed = point.proceed();
            log.info("执行信息: {}", proceed);
        } catch (Throwable e) {
            log.error("AOP 捕获了异常信息: ", e);
            throw new RuntimeException(e);
        }
    }
    /*
    `1. 先查数据库 -> 旧记录
     2. 新旧对比
     3. 更新新数据到数据库

     return xxx;

     4. 留存变更记录
     */

}
