package com.example.mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Matrix
 * @date 2022/8/15 10:32
 */
@Data
@TableName("sys_user")
public class SysUser {

    private Long id;

    private String name;

    private Integer age;

}
