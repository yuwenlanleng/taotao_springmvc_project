package com.taotao.portal.service;

import com.taotao.portal.wrap.OrderWrap;

import zhk_taotao_util.TaotaoResult;

public interface OrderService {
	/**
	 * 添加订单
	 * 
	 * @param orderWrap
	 * @return
	 */
	public TaotaoResult addOrder(OrderWrap orderWrap);
}
