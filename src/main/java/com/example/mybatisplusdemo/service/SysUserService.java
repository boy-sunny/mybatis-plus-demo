package com.example.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplusdemo.model.SysUser;

/**
 * @author Matrix
 * @date 2022/8/15 10:33
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getById(long id);

}
