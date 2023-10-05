package com.example.mybatisplusdemo.component;

import cn.hutool.json.JSONUtil;
import com.example.mybatisplusdemo.component.event.SysLogEvent;
import com.example.mybatisplusdemo.model.SysLog;
import com.example.mybatisplusdemo.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统日志监听器
 *
 * @author Oriental Ming
 * @date 2023/10/5 20:54
 */
// @Async
// @EnableAsync
@Component
@RequiredArgsConstructor
public class SysLogListener implements ApplicationListener<SysLogEvent> {

	private final SysLogService sysLogService;

	/**
	 * 不管主业务成功与否，单独做保存，不受主业务事务的影响
	 *
	 * @param event 日志事件
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void onApplicationEvent(SysLogEvent event) {
		System.out.println("监听事件线程 ID：" + Thread.currentThread().getId());
		String context = JSONUtil.toJsonStr(event.getSource());
		SysLog sysLog = new SysLog();
		sysLog.setContent(context);
		sysLogService.save(sysLog);
	}

}
