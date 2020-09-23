package com.wobangkj.provider;

import com.wobangkj.Const;
import com.wobangkj.api.ArgsParser;
import com.wobangkj.api.TokenGenerate;

import java.util.*;

/**
 * main解析器实现
 *
 * @author cliod
 * @since 9/23/20 11:10 AM
 */
public class SimpleArgsParserImpl implements ArgsParser {

	/**
	 * 简单命令
	 */
	private final List<String> commands = Arrays.asList("-c", "-h");
	private final List<String> version = Arrays.asList("docker");

	private Map<String, String> args;

	public SimpleArgsParserImpl(LinkedList<String> args) {
		if ((args.size() & 1) == 1) {
			args.pollLast();
		}
		this.args = new HashMap<>();
		if (args.size() > 0) {
			for (int i = 0; i < args.size(); i = i + 2) {
				this.args.put(args.get(i), args.get(i + 1));
			}
		}
	}

	public SimpleArgsParserImpl(String[] args) {
		this(new LinkedList<>(Arrays.asList(args)));
	}

	public static SimpleArgsParserImpl getInstance(String... args) {
		return new SimpleArgsParserImpl(args);
	}

	/**
	 * 获取解析结果
	 *
	 * @return 结果
	 */
	@Override
	public TokenGenerate getGenerate() {
		String command = "docker";
		if (this.args.containsKey(commands.get(0))) {
			command = this.args.get("-v");
		}
		if (!this.version.contains(command)) {
			command = this.version.get(0);
		}
		return DockerAware.getInstance(Const.config, command);
	}
}
