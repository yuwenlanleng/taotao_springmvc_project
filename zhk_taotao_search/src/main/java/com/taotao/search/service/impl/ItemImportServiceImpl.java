package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.service.ItemImportService;
import com.taotao.search.service.SearchItemService;
import com.taotao.search.wrap.ItemSearchWrap;

import zhk_taotao_util.ExceptionUtil;
import zhk_taotao_util.TaotaoResult;

@Service
public class ItemImportServiceImpl implements ItemImportService {

	@Autowired
	private SolrServer solrServer;

	@Autowired
	private SearchItemService searchItemService;

	@Override
	public TaotaoResult importAllItemList() {
		// TODO Auto-generated method stub
		try {
			List<ItemSearchWrap> itemSearchWrapList = searchItemService.getAllItemSearchWrap();

			for (ItemSearchWrap itemSearchWrap : itemSearchWrapList) {
				SolrInputDocument solrInputDocument = new SolrInputDocument();
				solrInputDocument.setField("id", itemSearchWrap.getId());
				solrInputDocument.setField("item_title", itemSearchWrap.getTitle());
				solrInputDocument.setField("item_sell_point", itemSearchWrap.getSellPoint());
				solrInputDocument.setField("item_price", itemSearchWrap.getPrice());
				solrInputDocument.setField("item_image", itemSearchWrap.getImage());
				solrInputDocument.setField("item_category_name", itemSearchWrap.getCategoryName());
				solrInputDocument.setField("item_desc", itemSearchWrap.getItemDesc());
				solrServer.add(solrInputDocument);
			}
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
