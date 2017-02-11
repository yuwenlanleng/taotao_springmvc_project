package com.taotao.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

import zhk_taotao_util.HttpClientUtil;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class UserServiceImpl implements UserService {

	@Value("${HTTPTOKENUSERBASEURL}")
	public String HTTPTOKENUSERBASEURL;

	@Value("${HTTPTOKENUSERGETUSERURL}")
	public String HTTPTOKENUSERGETUSERURL;

	@Value("${SET_SESSION_INDEX}")
	public String SET_SESSION_INDEX;

	@Value("${HTTPCLIENTGETUSERBYTOCKENURL}")
	private String HTTPCLIENTGETUSERBYTOCKENURL;

	@Override
	public TbUser getTbUserByToken(String tocken) {
		// TODO Auto-generated method stub
		String requestResult = HttpClientUtil.doGet(HTTPTOKENUSERBASEURL + HTTPCLIENTGETUSERBYTOCKENURL + tocken);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(requestResult, TbUser.class);
		if(taotaoResult.getStatus() == 200){
				TbUser user = (TbUser)taotaoResult.getData();
				return user;
		}
		return null;
	}

}
