package com.taotao.portal.service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.wrap.ItemInfoWrap;

public interface ItemService {
	/**
	 * 通过商品id得到商品信息
	 * 
	 * @param itemId
	 * @return
	 */
	public ItemInfoWrap getItemInfoByItemId(long itemId);

	/**
	 * 通过商品id得到商品描述
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItemDesc getItemDescByItemId(long itemId);

	/**
	 * 通过商品id得到商品规格
	 * 
	 * @param itemId
	 * @return
	 */
	public String getItemParamByItemId(long itemId);
}
