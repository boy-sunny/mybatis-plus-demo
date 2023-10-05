package com.example.mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统日志
 *
 * @author Oriental Ming
 * @date 2023/10/5 20:52
 */
@Data
@TableName("sys_log")
public class SysLog {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 内容
	 */
	private String content;

}
