package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.portal.wrap.CatCookieWrap;

import zhk_taotao_util.TaotaoResult;

public interface CartService {
	/**
	 * 向购物车中添加商品
	 * 
	 * @param itemId
	 * @return
	 */
	TaotaoResult addItemToCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 从缓存中把购物车的内容取出来
	 * 
	 * @param itemId
	 * @param request
	 * @return
	 */
	List<CatCookieWrap> getItemCatFromCookie(HttpServletRequest request);

	/**
	 * 删除cookie中的购物车商品
	 * 
	 * @param itemId
	 * @return
	 */
	TaotaoResult deleteItemCatCookie(long itemId,HttpServletRequest request,HttpServletResponse response);

	/**
	 * 购物车商品数量加1或减一
	 * @param num
	 * @return
	 */
	TaotaoResult incrementAndDecrement(int num,long itemId,HttpServletRequest request,HttpServletResponse response);


}
