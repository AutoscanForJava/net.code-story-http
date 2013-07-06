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

import static org.fest.assertions.Assertions.*;

import java.util.*;

import org.junit.*;

public class TemplateTest {
  @Test
  public void render() {
    assertThat(new Template("classpath:web/0variable.txt").render()).isEqualTo("0 variables");
    assertThat(new Template("classpath:web/1variable.txt").render("name", "Bob")).isEqualTo("Hello Bob");
    assertThat(new Template("classpath:web/2variables.txt").render("verb", "Hello", "name", "Bob")).isEqualTo("Hello Bob");
    assertThat(new Template("classpath:web/2variables.txt").render(new HashMap<String, Object>() {{
      put("verb", "Hello");
      put("name", 12);
    }})).isEqualTo("Hello 12");
  }
}
