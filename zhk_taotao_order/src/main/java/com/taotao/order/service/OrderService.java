package com.taotao.order.service;

import java.util.List;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import zhk_taotao_util.TaotaoResult;

public interface OrderService {
	/**
	 * 添加订单
	 * 
	 * @param orderWrap
	 * @return
	 */
	public TaotaoResult addOrder(TbOrder tbOrder, List<TbOrderItem> tbOrderItemList, TbOrderShipping tbOrderShipping);
}
