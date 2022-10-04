package com.example.mybatisplusdemo.service.impl;

import com.example.mybatisplusdemo.model.SysUserRole;
import com.example.mybatisplusdemo.service.SysUserRoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SysUserRoleServiceImplTest {

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Test
    @DisplayName("正常")
    void saveOrUpdating() {
        SysUserRole userRole = new SysUserRole(2L, 111L);
        assertThrows(RuntimeException.class, () -> sysUserRoleService.saveOrUpdating(userRole));
    }

    @Test
    @DisplayName("异常信息")
    void saveOrUpdatingThrow() {
        SysUserRole userRole = new SysUserRole(1L, 111L);
        assertThrows(RuntimeException.class, () -> sysUserRoleService.saveOrUpdating(userRole));
    }

}