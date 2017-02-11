package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.portal.service.OrderService;
import com.taotao.portal.wrap.OrderWrap;

import zhk_taotao_util.HttpClientUtil;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${HTTPCLIENTORDERBASEURL}")
	private String HTTPCLIENTORDERBASEURL;

	@Value("${HTTPCLIENTGETORDERURL}")
	private String HTTPCLIENTGETORDERURL;

	@Override
	public TaotaoResult addOrder(OrderWrap orderWrap) {
		// TODO Auto-generated method stub
		try {
			String result = HttpClientUtil.doPostJson(HTTPCLIENTORDERBASEURL + HTTPCLIENTGETORDERURL,
					JsonUtils.objectToJson(orderWrap));
			TaotaoResult taotaoResult = TaotaoResult.ok(result);
			return taotaoResult;
		} catch (Exception e) {
			return TaotaoResult.build(500, "创建订单失败");
		}
	}

}
