package com.taotao.service;

import java.util.List;

import zhk_taotao_common.ItemCatWrap;
import zhk_taotao_util.TaotaoResult;

/**
 * 前台页面内容管理service
 * 
 * @author 97282
 *
 */
public interface ContentCatService {
	/**
	 * 得到分类列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<ItemCatWrap> getContentItemCatList(long parentId);

	/**
	 * 添加前台内容分类
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	TaotaoResult addContentCat(long parentId, String name);

	/**
	 * 内容分类项
	 * 
	 * @param parentId
	 * @param id
	 * @return
	 */
	TaotaoResult deleteContentCat(long parentId, long id);

	/**
	 * 重命名内容分类
	 * 
	 * @param nodeId
	 * @param name
	 * @return
	 */
	TaotaoResult updateContentCat(long nodeId, String name);

}
