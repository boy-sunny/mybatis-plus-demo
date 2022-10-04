package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplusdemo.mapper.SysUserMapper;
import com.example.mybatisplusdemo.model.SysUser;
import com.example.mybatisplusdemo.model.SysUserRole;
import com.example.mybatisplusdemo.service.SysUserRoleService;
import com.example.mybatisplusdemo.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matrix
 * @date 2022/8/15 10:33
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserRoleService sysUserRoleService;

    @Override
    public SysUser getById(long id) {
        return baseMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAndClean(SysUser user) {
        boolean update = this.updateById(user);
        sysUserRoleService.saveOrUpdating(new SysUserRole(user.getId(), 123123L));
        return update;
    }


}
