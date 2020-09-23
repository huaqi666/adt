package com.wobangkj.provider;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.wobangkj.api.Printable;
import com.wobangkj.api.TokenGenerate;
import com.wobangkj.bean.TokenData;
import com.wobangkj.config.AccessConfig;
import com.wobangkj.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 生成docker临时密码
 *
 * @author cliod
 * @since 9/23/20 10:35 AM
 */
@Slf4j
public class DockerTokenV1Generate implements TokenGenerate {
	/**
	 * v1.0
	 * ak和as是docker@zhq帐号的.
	 *
	 * @since 2020-09-15
	 */
	private final IAcsClient client;
	private final CommonRequest request;

	public DockerTokenV1Generate(String regionId, String accessKeyId, String secret) {
		this(DefaultProfile.getProfile(regionId, accessKeyId, secret));
	}

	public DockerTokenV1Generate(AccessConfig config) {
		this(DefaultProfile.getProfile(config.getRegionId(), config.getAccessKeyId(), config.getSecret()));
	}

	public DockerTokenV1Generate(DefaultProfile profile) {
		client = new DefaultAcsClient(profile);
		// 旧版本 - 2016-06-07
		request = new CommonRequest();
		request.setSysProtocol(ProtocolType.HTTPS);
		request.setSysMethod(MethodType.GET);
		request.setSysDomain("cr.cn-hangzhou.aliyuncs.com");
		request.setSysVersion("2016-06-07");
		request.setSysUriPattern("/tokens");
		request.putHeadParameter("Content-Type", "application/json");
		String requestBody = "{}";
		request.setHttpContent(requestBody.getBytes(), "utf-8", FormatType.JSON);
	}

	/**
	 * 获取指定帐号的临时密码
	 *
	 * @return token(临时密码)
	 * @throws ClientException 异常
	 */
	@Override
	public Printable getToken() throws ClientException {
		CommonResponse response = client.getCommonResponse(request);
		log.debug(JsonUtils.toJson(response.getData()));
		return JsonUtils.getGoogleJson().fromJson(response.getData(), TokenData.class);
	}
}
