package com.example.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplusdemo.model.SysUserRole;

/**
 * @author Matrix
 * @date 2022/8/23 11:12
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    void saveOrUpdating(SysUserRole userRole);

}
