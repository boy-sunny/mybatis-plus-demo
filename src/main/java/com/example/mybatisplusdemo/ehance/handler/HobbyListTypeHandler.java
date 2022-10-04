package com.example.mybatisplusdemo.ehance.handler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.mybatisplusdemo.pojo.Hobby;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

/**
 * @author Sunny Boy
 * @date 2022/10/3 20:48
 */
public class HobbyListTypeHandler extends JacksonTypeHandler {

	public HobbyListTypeHandler(Class<?> type) {
		super(type);
	}

	@Override
	protected Object parse(String json) {
		try {
			return getObjectMapper().readValue(json, new TypeReference<List<Hobby>>() {});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
