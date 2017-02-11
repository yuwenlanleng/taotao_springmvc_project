package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.wrap.ItemInfoWrap;

import zhk_taotao_util.HttpClientUtil;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${HTTPCLIENTURL}")
	private String HTTPCLIENTURL;

	@Value("${HTTPCLIENTITEMINFO}")
	private String HTTPCLIENTITEMINFO;

	@Value("${HTTPCLIENTITEMDESC}")
	private String HTTPCLIENTITEMDESC;

	@Value("${HTTPCLIENTITEMPARAM}")
	private String HTTPCLIENTITEMPARAM;

	@Override
	public TbItemDesc getItemDescByItemId(long itemId) {
		// TODO Auto-generated method stub
		String httpClientResult = HttpClientUtil.doGet(HTTPCLIENTURL + HTTPCLIENTITEMDESC + itemId);
		TbItemDesc formatToPojo = JsonUtils.jsonToPojo(httpClientResult, TbItemDesc.class);
		return formatToPojo;
	}

	@Override
	public ItemInfoWrap getItemInfoByItemId(long itemId) {
		// TODO Auto-generated method stub
		String httpClientResult = HttpClientUtil.doGet(HTTPCLIENTURL + HTTPCLIENTITEMINFO + itemId);
		ItemInfoWrap jsonToPojo = JsonUtils.jsonToPojo(httpClientResult, ItemInfoWrap.class);
		return jsonToPojo;
	}

	@Override
	public String getItemParamByItemId(long itemId) {
		// TODO Auto-generated method stub
		String httpClientResult = HttpClientUtil.doGet(HTTPCLIENTURL + HTTPCLIENTITEMPARAM + itemId);
		TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(httpClientResult, TbItemParamItem.class);
		String paramData = itemParamItem.getParamData();
		// 生成html
		// 把规格参数json数据转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (Map m1 : jsonList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
			sb.append("        </tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for (Map m2 : list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
				sb.append("            <td>" + m2.get("v") + "</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		// 返回html片段
		return sb.toString();
	}

}
