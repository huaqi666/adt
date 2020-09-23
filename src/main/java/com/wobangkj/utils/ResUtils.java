package com.wobangkj.utils;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取classpath下的资源文件
 *
 * @author cliod
 * @since 9/23/20 11:48 AM
 */
public class ResUtils {

	public static InputStream getResources(String filename) {
		return ResUtils.class.getClassLoader().getResourceAsStream(filename);
	}

	public static Properties getProperties(String filename) throws IOException {
		Properties properties = new Properties();
		properties.load(getResources(filename));
		return properties;
	}
}
