package com.wobangkj;

import com.aliyuncs.exceptions.ClientException;
import com.wobangkj.utils.AliUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Hello world!
 *
 * @author cliod
 */
@Slf4j
public class TokenApp {
	public static void main(String[] args) throws ClientException, IOException {
		log.info("初始化...");
		Config.init();
		log.info("解析中...");
		AliUtils.getToken(args).print();
		log.info("完成并退出.");
	}
}
