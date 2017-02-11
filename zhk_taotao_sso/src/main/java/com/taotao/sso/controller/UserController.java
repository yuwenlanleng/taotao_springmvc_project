package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserLoginService;

import zhk_taotao_util.ExceptionUtil;
import zhk_taotao_util.TaotaoResult;

@Controller
public class UserController {

	@Autowired
	private UserLoginService userLoginService;

	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public TaotaoResult checkUserData(@PathVariable String param, @PathVariable Integer type) {
		TaotaoResult checkDateResult = userLoginService.checkDate(param, type);
		return checkDateResult;
	}

	@RequestMapping("/user/register")
	@ResponseBody
	public TaotaoResult addRegisterUser(TbUser tbUser) {
		TaotaoResult addRegisterUerResult = userLoginService.addRegisterUer(tbUser.getUsername(), tbUser.getPassword(),
				tbUser.getPhone(), tbUser.getEmail());
		return addRegisterUerResult;
	}

	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object getUserByTocken(@PathVariable String token, String callback) {
		TaotaoResult userByTokenResult;
		try {
			userByTokenResult = userLoginService.getUserByToken(token);
		} catch (Exception e) {
			userByTokenResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userByTokenResult);
		if (StringUtils.isEmpty(callback)) {
			return userByTokenResult;
		} else {
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

	@RequestMapping("/user/showLogin")
	public String showLoginPage() {
		// TaotaoResult userByTokenResult =
		// userLoginService.getUserByToken(tocken);
		return "login";
	}

	@RequestMapping("/user/showRegister")
	public String showRegister() {
		// TaotaoResult userByTokenResult =
		// userLoginService.getUserByToken(tocken);
		return "register";
	}

	@RequestMapping("/user/login")
	@ResponseBody
	public TaotaoResult showRegister(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		TaotaoResult userLoginResult = userLoginService.loginUser(username, password, request, response);
		return userLoginResult;
	}
}
