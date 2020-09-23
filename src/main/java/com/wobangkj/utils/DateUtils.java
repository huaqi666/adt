package com.wobangkj.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 时间工具类
 *
 * @author cliod
 * @since 9/23/20 1:06 PM
 */
public class DateUtils {
	public static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String getNow() {
		return LocalDateTime.now().format(DateUtils.DATETIME_FORMATTER);
	}

	public static String getNow(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String format(TemporalAccessor temporal, String pattern) {
		return DateTimeFormatter.ofPattern(pattern).format(temporal);
	}

	public static String format(TemporalAccessor temporal) {
		return DATETIME_FORMATTER.format(temporal);
	}
}
