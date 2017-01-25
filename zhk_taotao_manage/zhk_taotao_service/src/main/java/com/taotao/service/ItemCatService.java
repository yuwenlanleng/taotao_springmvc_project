package com.taotao.service;

import java.util.List;

import zhk_taotao_common.ItemCatWrap;

public interface ItemCatService {
	
	public List<ItemCatWrap> getItemCatWrapByParentId(long parentId);
}
