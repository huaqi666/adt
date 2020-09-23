package com.wobangkj;

import com.wobangkj.config.AccessConfig;
import com.wobangkj.utils.ResUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置和常量
 *
 * @author cliod
 * @since 9/23/20 11:58 AM
 */
public class Config {
	public static AccessConfig config;
	public static Properties properties;

	public static void init() throws IOException {
		config = new AccessConfig();
		properties = ResUtils.getProperties("application.properties");
		convert();
	}

	public static void convert() {
		config.setRegionId(properties.getProperty("ali.docker.regionId"));
		config.setAccessKeyId(properties.getProperty("ali.docker.accessKeyId"));
		config.setSecret(properties.getProperty("ali.docker.secret"));
	}
}
