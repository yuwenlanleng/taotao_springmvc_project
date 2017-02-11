package com.taotao.order.wrap;

import java.util.List;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

public class OrderWrap extends TbOrder {
	/**
	 * 订单详细信息
	 */
	private List<TbOrderItem> orderItems;

	/**
	 * 物流信息
	 */
	private TbOrderShipping tbOrderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getTbOrderShipping() {
		return tbOrderShipping;
	}

	public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
		this.tbOrderShipping = tbOrderShipping;
	}
	
}
