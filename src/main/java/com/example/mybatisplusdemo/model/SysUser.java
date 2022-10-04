package com.example.mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.mybatisplusdemo.ehance.handler.HobbyListTypeHandler;
import com.example.mybatisplusdemo.pojo.Hobby;
import com.example.mybatisplusdemo.pojo.OtherInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 系统用户信息
 *
 * @author Matrix
 * @date 2022/8/15 10:32
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_user", autoResultMap = true)
public class SysUser {

	@TableId
	private Long id;

	private String name;

	private Integer age;

	@TableField(typeHandler = JacksonTypeHandler.class)
	private OtherInfo otherInfo;

	@TableField(typeHandler = HobbyListTypeHandler.class)
	private List<Hobby> hobby;


}
