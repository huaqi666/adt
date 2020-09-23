package com.wobangkj.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wobangkj.api.Printable;
import com.wobangkj.api.SessionSerializable;
import com.wobangkj.api.TokenPrintable;
import com.wobangkj.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * 包装返回结果对象
 *
 * @author cliod
 * @since 2020-09-15
 */
@Data
public class TokenData implements SessionSerializable, Printable {
	private static final long serialVersionUID = 5698626105564820807L;
	private TokenInfo data;

	/**
	 * 打印输出结果
	 */
	@Override
	public void print() {
		data.print();
	}
}

/**
 * 包装返回结果对象
 * 订单顺序输出
 *
 * @author cliod
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"token", "expire"})
class TokenInfo extends TokenPrintable implements SessionSerializable {
	private static final long serialVersionUID = -4582126008468926217L;
	@JsonProperty("token")
	private String authorizationToken;
	@JsonIgnore
	private String tempUserName;
	@JsonIgnore
	private long expireDate;

	@Override
	public String getExpire() {
		return Instant.ofEpochMilli(this.expireDate).atZone(ZoneId.systemDefault()).format(DateUtils.DATETIME_FORMATTER);
	}

	public void setExpire(Date expire) {
		this.expireDate = expire.getTime();
	}

	/**
	 * 获取访问令牌
	 *
	 * @return 访问令牌
	 */
	@Override
	public String getToken() {
		return this.authorizationToken;
	}

	/**
	 * @see TokenInfo#toJson()
	 */
	@Override
	public String toString() {
		return this.toJson();
	}

}