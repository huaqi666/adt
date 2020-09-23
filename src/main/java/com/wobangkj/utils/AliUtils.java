package com.wobangkj.utils;

import com.aliyuncs.exceptions.ClientException;
import com.wobangkj.api.Printable;
import com.wobangkj.provider.SimpleArgsParserImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 解析参数, 获取打印结果
 *
 * @author cliod
 * @since 9/15/20 3:47 PM
 */
@Slf4j
public class AliUtils {

	/**
	 * 获取docker@zhq的临时密码
	 *
	 * @return 临时密码
	 * @throws ClientException 异常
	 */
	public static Printable getToken(String... args) throws ClientException {
		return SimpleArgsParserImpl.getInstance(args).getGenerate().getToken();
	}
}
