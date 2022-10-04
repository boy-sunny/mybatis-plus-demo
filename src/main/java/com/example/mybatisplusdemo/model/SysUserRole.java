package com.example.mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Matrix
 * @date 2022/8/23 11:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class SysUserRole {

    private Long userId;
    private Long roleId;

}
