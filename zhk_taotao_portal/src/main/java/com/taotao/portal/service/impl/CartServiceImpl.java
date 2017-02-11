package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.portal.service.CartService;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.wrap.CatCookieWrap;
import com.taotao.portal.wrap.ItemInfoWrap;

import zhk_taotao_common.CookieUtils;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ItemService ItemService;

	@Override
	public TaotaoResult addItemToCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 现从cookie中取购物车商品的信息
		CatCookieWrap catCookie = null;
		String catCookieWrapListFromCookie = getCatCookieWrapListFromCookie(request);
		List<CatCookieWrap> jsonToList = new ArrayList<>();
		if (catCookieWrapListFromCookie != null) {
			jsonToList = JsonUtils.jsonToList(catCookieWrapListFromCookie, CatCookieWrap.class);
			for (CatCookieWrap catCookieWrap : jsonToList) {
				if (catCookieWrap.getItemId() == itemId) {
					jsonToList.remove(catCookieWrap);
					catCookieWrap.setNum(num);
					catCookie = catCookieWrap;
					break;
				}
			}
		}
		if (catCookie == null) {
			// 如果cookie中没有再从数据库中查商品信息
			catCookie = new CatCookieWrap();
			ItemInfoWrap itemInfo = ItemService.getItemInfoByItemId(itemId);
			catCookie.setItemId(itemInfo.getId());
			catCookie.setSellPoint(itemInfo.getSellPoint());
			catCookie.setTitle(itemInfo.getTitle());
			catCookie.setPrice(itemInfo.getPrice());
			catCookie.setNum(num);
			catCookie.setImage(itemInfo.getImage() == null ? "" : itemInfo.getImage().split(",")[0]);
		}
		jsonToList.add(catCookie);
		String cookieResult = JsonUtils.objectToJson(jsonToList);
		CookieUtils.setCookie(request, response, "CAT_COOKIE", cookieResult);
		return TaotaoResult.ok();
	}

	String getCatCookieWrapListFromCookie(HttpServletRequest request) {
		String catCookieString = CookieUtils.getCookieValue(request, "CAT_COOKIE");
		return catCookieString;
	}

	@Override
	public List<CatCookieWrap> getItemCatFromCookie(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cookieResult = getCatCookieWrapListFromCookie(request);
		if (cookieResult != null) {
			List<CatCookieWrap> jsonToList = JsonUtils.jsonToList(cookieResult, CatCookieWrap.class);
			return jsonToList;
		}
		return null;
	}

	@Override
	public TaotaoResult deleteItemCatCookie(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CatCookieWrap catCookie = null;
		String catCookieWrapListFromCookie = getCatCookieWrapListFromCookie(request);
		List<CatCookieWrap> jsonToList = new ArrayList<>();
		if (catCookieWrapListFromCookie != null) {
			jsonToList = JsonUtils.jsonToList(catCookieWrapListFromCookie, CatCookieWrap.class);
			for (CatCookieWrap catCookieWrap : jsonToList) {
				if (catCookieWrap.getItemId() == itemId) {
					jsonToList.remove(catCookieWrap);
					break;
				}
			}
		}
		CookieUtils.setCookie(request, response, "CAT_COOKIE", JsonUtils.objectToJson(jsonToList));
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult incrementAndDecrement(int num, long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		CatCookieWrap catCookie = null;
		String catCookieWrapListFromCookie = getCatCookieWrapListFromCookie(request);
		List<CatCookieWrap> jsonToList = new ArrayList<>();
		if (catCookieWrapListFromCookie != null) {
			jsonToList = JsonUtils.jsonToList(catCookieWrapListFromCookie, CatCookieWrap.class);
			for (CatCookieWrap catCookieWrap : jsonToList) {
				if (catCookieWrap.getItemId() == itemId) {
					jsonToList.remove(catCookieWrap);
					catCookieWrap.setNum(catCookieWrap.getNum() + num);
					jsonToList.add(catCookieWrap);
					break;
				}
			}
		}
		CookieUtils.setCookie(request, response, "CAT_COOKIE", JsonUtils.objectToJson(jsonToList));
		return TaotaoResult.ok();
	}

}
