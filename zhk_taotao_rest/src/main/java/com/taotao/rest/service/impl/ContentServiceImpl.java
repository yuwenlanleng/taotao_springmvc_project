package com.taotao.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.SingleRedisDao;
import com.taotao.rest.dao.impl.SingleRedisDaoImpl;
import com.taotao.rest.service.ContentService;

import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;

	@Autowired
	private SingleRedisDaoImpl singleRedisDaoImpl;

	@Override
	public TaotaoResult getAllContentListByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		try {
			// 在缓存中查找
			String hgetResult = singleRedisDaoImpl.hget(categoryId);
			if (hgetResult != null) {
				// 把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(hgetResult, TbContent.class);
				return TaotaoResult.ok(resultList);
			}
		} catch (Exception e) {
			// 做相应的处理，例如发邮件或发短信告诉别人
			e.printStackTrace();
		}
		TbContentExample tbContentExample = new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
		try {
			String json = JsonUtils.objectToJson(tbContentList);
			singleRedisDaoImpl.hset(categoryId, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(tbContentList);
	}

}
