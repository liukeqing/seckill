package org.seckill.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
						"classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() {
		long id = 1000L;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}",seckill);
	}
	//测试代码完整逻辑，注意可重复执行。
	@Test
	public void testSeckillLogic(){
		long id = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}",exposer);
			long phone = 15911020862L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
				logger.info("result={}",seckillExecution);
			} catch (SeckillException e) {
				logger.error(e.getMessage());
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			} 
		}else{
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
	}
	@Test
	public void executeSeckillProcedure(){
		long seckillId =1003;
		long phone = 13552198787L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5 = exposer.getMd5();
			SeckillExecution execution = seckillService.executeSeckillByProcedure(seckillId, phone, md5);
			logger.info(execution.getStateInfo());
		}
	}
	
	
//
//	@Test
//	public void testExportSeckillUrl() {
//		long id = 1000;
//		Exposer exposer = seckillService.exportSeckillUrl(id);
//		logger.info("exposer={}",exposer);
//	}
//
//	@Test
//	public void testExecuteSeckill() {
//		long id = 1000L;
//		long phone = 15911020862L;
//		String md5 = "aeda30ac25a8294484756366f1137fe9";
//		try {
//			SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
//			logger.info("result={}",seckillExecution);
//		} catch (SeckillException e) {
//			logger.error(e.getMessage());
//		} catch (RepeatKillException e) {
//			logger.error(e.getMessage());
//		} 
//	}

}
