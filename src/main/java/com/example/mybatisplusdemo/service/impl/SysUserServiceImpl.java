package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplusdemo.mapper.SysUserMapper;
import com.example.mybatisplusdemo.model.SysUser;
import com.example.mybatisplusdemo.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author Matrix
 * @date 2022/8/15 10:33
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getById(long id) {
        return baseMapper.getById(id);
    }

}
