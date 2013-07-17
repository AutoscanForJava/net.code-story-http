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
package net.codestory.http.compilers;

import static org.fest.assertions.Assertions.*;

import java.io.*;

import org.junit.*;

public class MarkdownCompilerTest {
  Compiler markdownCompiler = Compiler.MARKDOWN;

  @Test
  public void empty() throws IOException {
    String html = markdownCompiler.compile("");

    assertThat(html).isEmpty();
  }

  @Test
  public void markdown_to_html() throws IOException {
    String css = markdownCompiler.compile("This is **bold**");

    assertThat(css).isEqualTo("<p>This is <strong>bold</strong></p>\n");
  }
}
