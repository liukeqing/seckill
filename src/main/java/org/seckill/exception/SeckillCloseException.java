package org.seckill.exception;

/**
 * 秒杀已经关闭异常
 * @author liukeqing
 *
 */
public class SeckillCloseException extends RuntimeException{
	private static final long serialVersionUID = 1620390582941636829L;

	public SeckillCloseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SeckillCloseException(String message) {
		super(message);
	}

	
}
