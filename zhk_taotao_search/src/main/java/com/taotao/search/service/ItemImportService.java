package com.taotao.search.service;

import zhk_taotao_util.TaotaoResult;

public interface ItemImportService {
	/**
	 * 把查询到的list导入到索引库
	 * 
	 * @return
	 */
	public TaotaoResult importAllItemList();

}
