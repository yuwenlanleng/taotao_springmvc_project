package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.SingleRedisDao;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import zhk_taotao_util.TaotaoResult;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Autowired
	private TbOrderShippingMapper tOrderShippingMapper;

	@Autowired
	private SingleRedisDao singleRedisDao;

	@Value("${GEN_ID_KEY}")
	private String GEN_ID_KEY;

	@Value("${GEN_ID_INIT_VALUE}")
	private String GEN_ID_INIT_VALUE;

	@Override
	public TaotaoResult addOrder(TbOrder tbOrder, List<TbOrderItem> tbOrderItemList, TbOrderShipping tbOrderShipping) {
		// TODO Auto-generated method stub
		try {
			//对订单赋值
			Date createDate = new Date();
			// 0：未评价 1：已评价
			tbOrder.setBuyerRate(0);
			tbOrder.setCreateTime(createDate);
			tbOrder.setUpdateTime(createDate);
			if (StringUtils.isEmpty(singleRedisDao.getValue(GEN_ID_KEY))){
				singleRedisDao.setValue(GEN_ID_KEY, GEN_ID_INIT_VALUE);
			} 
			long orderId =singleRedisDao.incr(GEN_ID_KEY);
			tbOrder.setOrderId(String.valueOf(orderId));
			//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
			tbOrder.setStatus(1);
			tbOrderMapper.insert(tbOrder);
			for (TbOrderItem tbOrderItem2 : tbOrderItemList) {
				//对订单详情赋值
				tbOrderItem2.setId(String.valueOf(singleRedisDao.incr(GEN_ID_KEY)));
				tbOrderItem2.setOrderId((String.valueOf(orderId)));
				tbOrderItem2.setTotalFee(tbOrderItem2.getNum()*tbOrderItem2.getPrice());
				tbOrderItemMapper.insert(tbOrderItem2);
			}
			
			//对物流信息赋值
			tbOrderShipping.setCreated(createDate);
			tbOrderShipping.setOrderId(String.valueOf(orderId));
			tbOrderShipping.setUpdated(createDate);
			tOrderShippingMapper.insert(tbOrderShipping);
			return TaotaoResult.ok(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "增加订单失败");
		}
	}

}
