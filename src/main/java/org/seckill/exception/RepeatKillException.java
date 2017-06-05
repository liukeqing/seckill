package org.seckill.exception;

/**
 * 重复秒杀异常
 * @author liukeqing
 *
 */
public class RepeatKillException extends RuntimeException{

	
	private static final long serialVersionUID = 1457356743956329471L;

	public RepeatKillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RepeatKillException(String message) {
		super(message);
	}
	
	
}
