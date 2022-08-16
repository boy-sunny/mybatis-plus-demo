package com.example.mybatisplusdemo.service;

import com.example.mybatisplusdemo.model.SysUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserServiceTest {

    @Autowired
    SysUserService sysUserService;

    @Test
    @DisplayName("自定义SQL是否可以捕获到")
    void getById() {
        SysUser user = sysUserService.getById(2);
        assertNotNull(user);
    }

    @Test
    void all() {
        List<SysUser> users = sysUserService.list();
        assertFalse(users.isEmpty());
    }

    @Test
    @Transactional
    void add() {
        SysUser user = new SysUser();
        user.setName("伪装者");
        user.setAge(12);
        boolean saveSuccess = sysUserService.save(user);
        assertTrue(saveSuccess);

        boolean exists = sysUserService.lambdaQuery().eq(SysUser::getName, user.getName()).exists();
        assertTrue(exists);
    }

    @Test
    @Transactional
    void update() {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setName("可可");
        user.setAge(99);
        boolean result = sysUserService.updateById(user);
        assertTrue(result);

        boolean exists = sysUserService.lambdaQuery().eq(SysUser::getName, user.getName()).exists();
        assertTrue(exists);
    }

    @Test
    @Transactional
    void delete() {
        long id = 2;
        boolean result = sysUserService.removeById(id);
        assertTrue(result);

        SysUser user = sysUserService.getById(id);
        assertNull(user);
    }

}