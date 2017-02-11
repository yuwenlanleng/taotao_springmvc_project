package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.druid.util.StringUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.impl.SingleRedisDaoImpl;
import com.taotao.sso.service.UserLoginService;

import zhk_taotao_common.CookieUtils;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	TbUserMapper tbUserMapper;

	@Autowired
	private SingleRedisDaoImpl singleRedisDaoImpl;

	@Value("${SET_SESSION_INDEX}")
	private String SET_SESSION_INDEX;

	@Value("${SET_SESSION_EXPIRE_TIME}")
	private int SET_SESSION_EXPIRE_TIME;

	@Override
	public TaotaoResult checkDate(String param, int type) {
		// TODO Auto-generated method stub
		// 可选参数1、2、3分别代表username、phone、email
		TbUserExample tbUserExample = new TbUserExample();
		Criteria criteria = tbUserExample.createCriteria();
		TaotaoResult result = null;
		if (StringUtils.isEmpty(param)) {
			result = TaotaoResult.build(500, "参数不能为空");
			return result;
		}
		if (1 == type) {
			criteria.andUsernameEqualTo(param);
			result = TaotaoResult.build(500, "用户名重复", false);
		} else if (2 == type) {
			criteria.andPhoneEqualTo(param);
			result = TaotaoResult.build(500, "用户名重复", false);
		} else if (3 == type) {
			criteria.andEmailEqualTo(param);
			result = TaotaoResult.build(500, "用户名重复");
		}
		List<TbUser> tbUerList = tbUserMapper.selectByExample(tbUserExample);
		if (tbUerList != null && tbUerList.size() > 0) {
			return result;
		}
		result = TaotaoResult.ok(true);

		return result;
	}

	@Override
	public TaotaoResult loginUser(String username, String password,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		TbUserExample tbUserExample = new TbUserExample();
		Criteria criteria = tbUserExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> tbuserList = tbUserMapper.selectByExample(tbUserExample);
		TbUser tbUser = null;
		String realPassword = "";
		if (tbuserList != null && tbuserList.size() > 0) {
			tbUser = tbuserList.get(0);
			realPassword = tbUser.getPassword();
			if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(realPassword)) {
				String tocken = UUID.randomUUID().toString();
				tbUser.setPassword(null);
				singleRedisDaoImpl.setValue(SET_SESSION_INDEX + tocken, JsonUtils.objectToJson(tbUser));
				singleRedisDaoImpl.expire(SET_SESSION_INDEX + tocken, SET_SESSION_EXPIRE_TIME);
				CookieUtils.setCookie(request, response, "TT_LOGIN_COOKIE", tocken);
				return TaotaoResult.ok();
			}
		}

		return TaotaoResult.build(500, "用户名或密码错误");
	}

	@Override
	public TaotaoResult addRegisterUer(String username, String password, String phone, String email) {
		// TODO Auto-generated method stub
		Date createDate = new Date();
		TbUser tbUser = new TbUser();
		tbUser.setUsername(username);
		tbUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		tbUser.setPhone(phone);
		tbUser.setEmail(email);
		tbUser.setCreated(createDate);
		tbUser.setUpdated(createDate);
		tbUserMapper.insert(tbUser);
		return TaotaoResult.ok(tbUser);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		// TODO Auto-generated method stub
		String userInfo = singleRedisDaoImpl.getValue(SET_SESSION_INDEX + token);
		if (!StringUtils.isEmpty(userInfo)) {
			TbUser user = JsonUtils.jsonToPojo(userInfo, TbUser.class);
			singleRedisDaoImpl.expire(SET_SESSION_INDEX + token, SET_SESSION_EXPIRE_TIME);
			return TaotaoResult.ok(user);
		}
		return TaotaoResult.build(500, "用户信息已过期，请重新登录");
	}

}