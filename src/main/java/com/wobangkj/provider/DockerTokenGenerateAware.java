package com.wobangkj.provider;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.wobangkj.api.Printable;
import com.wobangkj.api.TokenGenerate;
import com.wobangkj.config.AccessConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 门面模式
 *
 * @author cliod
 * @since 9/23/20 11:24 AM
 */
@Slf4j
public class DockerTokenGenerateAware implements TokenGenerate {
	private final String version;
	private final DefaultProfile profile;

	public DockerTokenGenerateAware(DefaultProfile profile, String version) {
		this.version = version;
		this.profile = profile;
	}

	public DockerTokenGenerateAware(AccessConfig config, String version) {
		this.version = version;
		this.profile = DefaultProfile.getProfile(config.getRegionId(), config.getAccessKeyId(), config.getSecret());
	}

	/**
	 * 获取指定帐号的临时密码
	 *
	 * @return token(临时密码)
	 * @throws ClientException 异常
	 */
	@Override
	@SneakyThrows
	public Printable getToken() throws ClientException {
		Class<?> clazz = Class.forName(String.format("com.wobangkj.provider.DockerTokenV%sGenerate", this.version));
		return ((TokenGenerate) clazz.getConstructor(DefaultProfile.class).newInstance(this.profile)).getToken();
	}
}
