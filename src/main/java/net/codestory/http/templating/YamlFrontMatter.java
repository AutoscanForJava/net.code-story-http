/**
 * Copyright (C) 2013 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.http.templating;

import net.codestory.http.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static net.codestory.http.io.Strings.*;

public class YamlFrontMatter {
	private static final String SEPARATOR = "---\n";

	private final String name;
	private final String content;
	private final Map<String, Object> variables;

	private YamlFrontMatter(String name, String content, Map<String, Object> variables) {
		this.name = name;
		this.content = content;
		this.variables = new HashMap<>(variables);
		this.variables.put("content", content);
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public static YamlFrontMatter parse(Path path) throws IOException {
		return parse(path.getFileName().toString(), Resources.read(path, StandardCharsets.UTF_8));
	}

	public static YamlFrontMatter parse(String name, String content) {
		if (countMatches(content, SEPARATOR) < 2) {
			return new YamlFrontMatter(name, content, Collections.emptyMap());
		}
		return new YamlFrontMatter(name, stripHeader(content), parseVariables(content));
	}

	private static String stripHeader(String content) {
		return substringAfter(substringAfter(content, SEPARATOR), SEPARATOR);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> parseVariables(String content) {
		String header = substringBetween(content, SEPARATOR, SEPARATOR);

		return new YamlParser().parse(header);
	}
}
