package com.wobangkj.config;

import lombok.Data;

/**
 * ali授权信息
 *
 * @author cliod
 * @since 9/23/20 11:43 AM
 */
@Data
public class AccessConfig {

	private String regionId;
	private String accessKeyId;
	private String secret;
}
