package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhk_taotao_util.TaotaoResult;

public interface UserLoginService {
	/**
	 * 判断用户名，电话，email的校验，非空且不能重复
	 * 
	 * @param param
	 * @param type
	 * @return
	 */
	public TaotaoResult checkDate(String param, int type);

	/**
	 * 对用户登录做验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public TaotaoResult loginUser(String username, String password,HttpServletRequest request,HttpServletResponse response);

	/**
	 * 用户注册
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	public TaotaoResult addRegisterUer(String username, String password, String phone, String email);

	/**
	 * 通过token查询用户
	 * 
	 * @param token
	 * @return
	 */
	public TaotaoResult getUserByToken(String token);
}
