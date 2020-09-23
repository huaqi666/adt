package com.wobangkj.api;

import lombok.extern.slf4j.Slf4j;

/**
 * 可打印的token
 *
 * @author cliod
 * @since 9/23/20 10:22 AM
 */
@Slf4j
public abstract class TokenPrintable implements Printable {

	/**
	 * 从指定格式中输出
	 */
	@Override
	public void print() {
		log.info("获取临时密码...");
		log.info(String.format("\n" +
				"[注意事项]: 访问令牌即为临时密码. 只有在过期时间内使用才有效. \n" +
				"[访问令牌]: %s \n" +
				"[过期时间]: %s ", getToken(), getExpire()));
		log.info("临时密码获取成功.");
	}

	/**
	 * 获取过期时间
	 *
	 * @return 过期时间
	 */
	public abstract String getExpire();

	/**
	 * 获取访问令牌
	 *
	 * @return 访问令牌
	 */
	public abstract String getToken();
}
