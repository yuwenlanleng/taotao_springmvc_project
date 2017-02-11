package com.taotao.rest.service;

import com.taotao.pojo.TbItemParamItem;

public interface ItemParamService {

	/**
	 * 通过商品Id获得商品规格参数
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItemParamItem getItemParam(long itemId);
}
