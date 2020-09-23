package com.wobangkj.provider;

import com.wobangkj.Config;
import com.wobangkj.api.ArgsParser;
import com.wobangkj.api.TokenGenerate;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	private final List<String> shortCommands = Arrays.asList("a", "s", "r", "h", "p");
	private final List<String> longCommands = Arrays.asList("accessKeyId", "secret", "regionId", "help", "profile");
	private final List<String> commands = new ArrayList<>();

	private final Map<String, String> args;

	private final int a = 0;
	private final int s = 1;
	private final int r = 2;
	private final int h = 3;
	private final int p = 4;

	public SimpleArgsParserImpl(LinkedList<String> args) {
		if ((args.size() & 1) == 1) {
			args.pollLast();
		}
		this.args = new HashMap<>();
		commands.addAll(shortCommands);
		commands.addAll(longCommands);
		if (args.size() > 0) {
			String key;
			String value;
			for (int i = 0; i < args.size(); i = i + 2) {
				key = args.get(i);
				value = args.get(i + 1);
				if (this.args.containsKey(key)) {continue;} else {
					key = key.replaceAll("-", "");
				}
				if (StringUtils.isBlank(key)) {continue;}
				if (StringUtils.isBlank(value)) {continue;}
				if (!commands.contains(key)) { continue; }
				if (value.startsWith("-")) {continue;}
				this.args.put(key, value);
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
		iterParse();
		String command = "docker";
		return DockerAware.getInstance(Config.config, command);
	}

	@SneakyThrows
	private void iterParse() {
		String value;
		if (this.args.containsKey(this.longCommands.get(p))) {
			value = this.args.remove(this.longCommands.get(p));
			this.args.remove(this.shortCommands.get(p));
		} else if (this.args.containsKey(this.shortCommands.get(p))) {
			value = this.args.remove(this.shortCommands.get(p));
		} else {
			value = "";
		}
		if (StringUtils.isNotEmpty(value)) {
			setFromFile(value);
			return;
		}
		setFromCommand();
	}

	private void setFromCommand() {
		if (this.args.containsKey(this.shortCommands.get(a))) {
			Config.config.setAccessKeyId(this.args.get(this.shortCommands.get(a)));
		}
		if (this.args.containsKey(this.longCommands.get(a))) {
			Config.config.setAccessKeyId(this.args.get(this.longCommands.get(a)));
		}
		if (this.args.containsKey(this.shortCommands.get(s))) {
			Config.config.setSecret(this.args.get(this.shortCommands.get(s)));
		}
		if (this.args.containsKey(this.longCommands.get(s))) {
			Config.config.setSecret(this.args.get(this.longCommands.get(s)));
		}
		if (this.args.containsKey(this.shortCommands.get(r))) {
			Config.config.setRegionId(this.args.get(this.shortCommands.get(r)));
		}
		if (this.args.containsKey(this.longCommands.get(r))) {
			Config.config.setRegionId(this.args.get(this.longCommands.get(r)));
		}
	}

	private void setFromFile(String value) throws IOException {
		File file = new File(value);
		Config.properties.load(new FileInputStream(file));
		Config.convert();
	}
}
