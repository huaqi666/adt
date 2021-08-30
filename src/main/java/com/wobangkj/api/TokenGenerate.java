package com.wobangkj.api;

import com.aliyuncs.exceptions.ClientException;

/**
 * 获取token
 *
 * @author cliod
 * @since 9/23/20 10:29 AM
 */
public interface TokenGenerate extends Generate<Printable> {
	/**
	 * 获取指定帐号的临时密码
	 *
	 * @return token(临时密码)
	 * @throws ClientException 异常
	 */
	Printable getToken() throws ClientException;

	/**
	 * 生成对象
	 *
	 * @return 对象
	 * @throws ClientException 异常
	 */
	@Override
	default Printable generate() throws ClientException {
		return this.getToken();
	}
}
