package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		/**
		 * 第一次成功插入
		 * 第二次由于联合主键的作用导致，插入失败，但是在sql语句中使用ignore语法，所以忽略错误，返回值0
		 */
		int insertCount = successKilledDao.insertSuccessKilled(1001L, 15911020859L);
		System.out.println("insertCount = " + insertCount);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		//此处的结果集填充是复杂的设计到了更深层次的嵌套
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1001L, 15911020859L);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
	}

	

}
