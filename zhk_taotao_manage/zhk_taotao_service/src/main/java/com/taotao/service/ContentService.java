package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbContent;

import zhk_taotao_common.ItemWrap;
import zhk_taotao_util.TaotaoResult;

public interface ContentService {

	/**
	 * 根据分类分页获取所有内容项
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	ItemWrap getAllContentByPage(long categoryId, int page, int rows);

	/**
	 * 添加内容信息
	 * 
	 * @param tbContent
	 * @return
	 */
	TaotaoResult addContent(TbContent tbContent);

}
