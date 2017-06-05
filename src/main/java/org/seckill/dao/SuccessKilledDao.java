package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	/**
	 * 查询购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return 插入的行数 
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
	/**
	 * 根据ID查询购买明细并携带秒杀产品对象的实体
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);	
	
	
}
