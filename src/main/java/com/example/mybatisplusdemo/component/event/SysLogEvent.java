package com.example.mybatisplusdemo.component.event;

import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author Oriental Ming
 * @date 2023/10/5 20:53
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(Object source) {
		super(source);
	}

}
