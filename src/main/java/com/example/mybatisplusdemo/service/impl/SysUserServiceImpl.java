package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplusdemo.component.event.SysLogEvent;
import com.example.mybatisplusdemo.mapper.SysUserMapper;
import com.example.mybatisplusdemo.model.SysUser;
import com.example.mybatisplusdemo.model.SysUserRole;
import com.example.mybatisplusdemo.service.SysUserRoleService;
import com.example.mybatisplusdemo.service.SysUserService;
import com.example.mybatisplusdemo.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matrix
 * @date 2022/8/15 10:33
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private final TransactionUtil transactionUtil;
	private final ApplicationContext applicationContext;
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveAndRecordLog(SysUser user) {
		System.out.println("saveAndRecordLog 线程 ID：" + Thread.currentThread().getId());
		save(user);
		applicationContext.publishEvent(new SysLogEvent(user));
		int i = 1 / 0;
	}

	@Override
	public void saveOfProgram(SysUser user) {
		// transactionUtil.execute(() -> {
		// 	save(user);
		// 	int i = 1 / 0;
		// });

		// transactionUtil.execute(() -> {
		// 	boolean save = save(user);
		// 	int i = 1 / 0;
		// 	return save;
		// });

		transactionUtil.execute(() -> {
			boolean save = save(user);
			int i = 1 / 0;
		}, t -> {
			log.error("失败了，原因：", t);
		});
	}


}
