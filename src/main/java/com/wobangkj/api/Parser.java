package com.wobangkj.api;

/**
 * 解析器
 *
 * @author cliod
 * @since 9/23/20 11:07 AM
 */
public interface Parser<T> {

	/**
	 * 解析
	 *
	 * @return 结果
	 */
	T parse();
}
