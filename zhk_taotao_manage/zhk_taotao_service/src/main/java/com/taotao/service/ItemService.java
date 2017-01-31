package com.taotao.service;

import com.taotao.pojo.TbItem;

import zhk_taotao_common.ItemWrap;
import zhk_taotao_util.TaotaoResult;

public interface ItemService {
	/**
	 * 通过商品id来查询商品
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItem getTbItemByItemId(long itemId);

	/**
	 * 通过分页查询商品列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public ItemWrap getItemList(int page, int rows);

	/**
	 * 添加商品
	 * 
	 * @param item
	 * @return
	 */
	TaotaoResult addItem(TbItem item);
}
