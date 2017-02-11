package com.taotao.rest.service;

import com.taotao.pojo.TbItemDesc;

public interface ItemDescService {
	/**
	 * 通过商品id获得商品描述
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItemDesc getItemDescByItemId(long itemId);
}
