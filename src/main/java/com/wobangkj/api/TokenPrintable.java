package com.wobangkj.api;

/**
 * 可打印的token
 *
 * @author cliod
 * @since 9/23/20 10:22 AM
 */
public interface TokenPrintable extends Printable {

	/**
	 * 从指定格式中输出
	 *
	 * @return 格式
	 */
	default String fromFormat() {
		return String.format("\n" +
				"[注意事项]: 访问令牌即为临时密码. 只有在过期时间内使用才有效. \n" +
				"[访问令牌]: %s \n" +
				"[过期时间]: %s ", getToken(), getExpire());
	}

	/**
	 * 获取过期时间
	 *
	 * @return 过期时间
	 */
	String getExpire();

	/**
	 * 获取访问令牌
	 *
	 * @return 访问令牌
	 */
	String getToken();
}
