package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplusdemo.mapper.SysLogMapper;
import com.example.mybatisplusdemo.model.SysLog;
import com.example.mybatisplusdemo.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * @author Oriental Ming
 * @date 2023/10/5 20:56
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {}
