package com.example.mybatisplusdemo.service;

import com.example.mybatisplusdemo.model.SysUser;
import com.example.mybatisplusdemo.pojo.Hobby;
import com.example.mybatisplusdemo.pojo.OtherInfo;
import org.assertj.core.util.Lists;
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
    void updateAndClean() {
        SysUser user = new SysUser();
        user.setId(2L);
        user.setName("天一");
        assertThrows(RuntimeException.class, () -> sysUserService.updateAndClean(user));
    }

    @Test
    void save() {
        SysUser user = new SysUser();
        user.setName("小孩");
        user.setOtherInfo(new OtherInfo("大连", "大连理工学院"));
        user.setHobby(Lists.newArrayList(new Hobby("篮球", "每天都玩")));
        sysUserService.save(user);
        boolean exists = sysUserService.lambdaQuery().eq(SysUser::getName, user.getName()).exists();
        assertTrue(exists);
    }

    @Test
    @DisplayName("自定义SQL是否可以捕获到")
    void getById() {
        SysUser user = sysUserService.getById(1576921063688818689L);
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