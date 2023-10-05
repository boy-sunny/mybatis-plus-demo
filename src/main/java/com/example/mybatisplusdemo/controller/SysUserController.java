package com.example.mybatisplusdemo.controller;

import com.example.mybatisplusdemo.model.SysUser;
import com.example.mybatisplusdemo.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sunny Boy
 * @date 2022/10/3 21:21
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserController {

	private final SysUserService userService;

	@GetMapping
	public SysUser getById(@RequestParam Long id) {
		return userService.getById(id);
	}

	@PostMapping("/saveAndRecordLog")
	public void saveAndRecordLog(@RequestBody SysUser user) {
		userService.saveAndRecordLog(user);
	}

	@PostMapping("/save")
	public void save(@RequestBody SysUser user) {
		userService.saveOfProgram(user);
	}

}
