package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbItemCat;

import zhk_taotao_common.ItemCatWrap;

public interface ItemCatService {
	
	List<ItemCatWrap> getItemCatWrapByParentId(long parentId);
	TbItemCat getItemCatByCatId(long itemCatId);
	
}
