package com.example.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Result;
import com.example.utils.GirlException;
import com.example.utils.ResultUtil;

@ControllerAdvice
public class ExpectionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExpectionHandler.class);

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public Result handler(Exception e) {
		if (e instanceof GirlException) {
			GirlException girlException = (GirlException) e;
			return ResultUtil.error(girlException.getCode(), girlException.getMessage());
		}
		logger.error("[系统异常]:{}", e);
		return ResultUtil.error(-1, "未知错误");
	}
}
