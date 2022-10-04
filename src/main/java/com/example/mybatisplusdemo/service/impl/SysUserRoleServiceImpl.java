package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplusdemo.mapper.SysUserRoleMapper;
import com.example.mybatisplusdemo.model.SysUserRole;
import com.example.mybatisplusdemo.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matrix
 * @date 2022/8/23 11:12
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdating(SysUserRole userRole) {
		LambdaQueryWrapper<SysUserRole> wrapper = Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, userRole.getUserId());

		this.update(userRole, wrapper);

		userRole.setRoleId(userRole.getRoleId() + 1);
		save(userRole);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SysUserRole entity) {
		boolean save = super.save(entity);
		if (save) {
			throw new RuntimeException("测试回滚");
		}

		return save;
	}


}
