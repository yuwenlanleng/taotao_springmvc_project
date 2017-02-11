package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;

import zhk_taotao_common.CookieUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String token = CookieUtils.getCookieValue(request, "TT_LOGIN_COOKIE");
		TbUser tbUser = userServiceImpl.getTbUserByToken(token);
		if (tbUser == null) {
			response.sendRedirect(userServiceImpl.HTTPTOKENUSERBASEURL + userServiceImpl.HTTPTOKENUSERGETUSERURL
					+ "?redirect=" + request.getRequestURL());
			return false;
		}
		// 取到用户信息，放行
		// 把用户信息放入Request
		request.setAttribute("user", tbUser);
		// 返回值决定handler是否执行。true：执行，false：不执行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
