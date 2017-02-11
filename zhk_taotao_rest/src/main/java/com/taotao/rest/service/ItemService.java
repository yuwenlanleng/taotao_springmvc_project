package com.taotao.rest.service;

import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 通过商品id得到商品信息
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItem getItemInfoByItemId(long itemId);
}
