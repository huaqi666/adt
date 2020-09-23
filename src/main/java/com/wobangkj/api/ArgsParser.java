package com.wobangkj.api;

/**
 * main参数解析器
 *
 * @author cliod
 * @since 9/23/20 11:08 AM
 */
public interface ArgsParser extends Parser<TokenGenerate> {

	/**
	 * 获取解析结果
	 *
	 * @return 结果
	 */
	TokenGenerate getGenerate();

	/**
	 * 解析
	 *
	 * @return 结果
	 */
	@Override
	default TokenGenerate parse() {
		return getGenerate();
	}
}
