package com.wobangkj.provider;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.wobangkj.api.Printable;
import com.wobangkj.api.TokenGenerate;
import com.wobangkj.config.AccessConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 门面模式
 *
 * @author cliod
 * @since 9/23/20 11:24 AM
 */
@Slf4j
public class DockerAware implements TokenGenerate {
	/**
	 * 门面对象
	 */
	protected static final DockerAware AWARE = new DockerAware();
	/**
	 * 命令缓存
	 */
	private final Map<String, String> map;
	/**
	 * 命令
	 */
	private String command;
	private DefaultProfile profile;

	protected DockerAware() {
		map = new HashMap<>(4);
		map.put("docker", "DockerTokenGenerate");
	}

	public static DockerAware getInstance(AccessConfig config, String command) {
		AWARE.command = command;
		AWARE.profile = DefaultProfile.getProfile(config.getRegionId(), config.getAccessKeyId(), config.getSecret());
		return AWARE;
	}

	public static DockerAware getInstance(DefaultProfile profile, String command) {
		AWARE.command = command;
		AWARE.profile = profile;
		return AWARE;
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
		Class<?> clazz = Class.forName(String.format("com.wobangkj.provider.%s", this.getClassName()));
		TokenGenerate generate = (TokenGenerate) clazz.getConstructor(DefaultProfile.class).newInstance(this.profile);
		return generate.getToken();
	}

	protected String getClassName() {
		return map.get(this.command);
	}
}
