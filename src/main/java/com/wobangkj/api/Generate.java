package com.wobangkj.api;

/**
 * 生成器
 *
 * @author cliod
 * @since 9/23/20 10:30 AM
 */
public interface Generate<T> {

	/**
	 * 生成对象
	 *
	 * @return 对象
	 * @throws Throwable 异常
	 */
	T generate() throws Throwable;
}
