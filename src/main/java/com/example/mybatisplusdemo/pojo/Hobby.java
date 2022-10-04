package com.example.mybatisplusdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 爱好信息
 *
 * @author Sunny Boy
 * @date 2022/10/3 20:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hobby {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String desc;

}
