package org.seckill.exception;
/**
 * 秒杀基础异常
 * @author liukeqing
 *
 */
public class SeckillException extends RuntimeException{

	private static final long serialVersionUID = -3103223884141334958L;

	public SeckillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SeckillException(String message) {
		super(message);
	}
	
}
