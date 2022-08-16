package com.example.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplusdemo.extend.annotation.DataPermissions;
import com.example.mybatisplusdemo.model.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author Matrix
 * @date 2022/8/15 10:34
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @DataPermissions(expression = "age > 18")
    SysUser getById(@Param("id") Long id);

}
