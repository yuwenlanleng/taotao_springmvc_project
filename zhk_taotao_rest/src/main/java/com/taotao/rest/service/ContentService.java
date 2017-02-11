package com.taotao.rest.service;

import zhk_taotao_util.TaotaoResult;

public interface ContentService {
	
	/**
	 * 获取内容列表
	 * 
	 * @param categoryId
	 * @return
	 */
	TaotaoResult getAllContentListByCategoryId(long categoryId);

}
