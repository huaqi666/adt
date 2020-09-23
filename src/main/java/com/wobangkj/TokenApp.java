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
		Const.init();
		log.info("获取临时密码...");
		AliUtils.getToken(args).print();
		log.info("临时密码获取成功.");
	}
}
