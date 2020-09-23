package com.wobangkj.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wobangkj.api.SessionSerializable;
import com.wobangkj.api.TokenPrintable;
import com.wobangkj.utils.DateUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * 包装返回结果对象
 *
 * @author cliod
 * @since 9/23/20 10:27 AM
 */
@Data
@Slf4j
public class TokenResponse implements SessionSerializable, TokenPrintable {
	private static final long serialVersionUID = 121135636659048829L;
	@JsonProperty("token")
	private String authorizationToken;
	@JsonIgnore
	private String code;
	@JsonIgnore
	private String expireTime;
	@JsonIgnore
	private String isSuccess;
	@JsonIgnore
	private String requestId;
	@JsonIgnore
	private String tempUsername;

	/**
	 * 获取访问令牌
	 *
	 * @return 访问令牌
	 */
	@Override
	public String getToken() {
		return authorizationToken;
	}

	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public String getExpire() {
		return Instant.ofEpochMilli(Long.parseLong(this.expireTime)).atZone(ZoneId.systemDefault()).format(DateUtils.DATETIME_FORMATTER);
	}

	public void setExpire(Date expire) {
		this.expireTime = String.valueOf(expire.getTime());
	}

	/**
	 * 打印输出结果
	 */
	@Override
	public void print() {
		log.info(fromFormat());
	}
}
