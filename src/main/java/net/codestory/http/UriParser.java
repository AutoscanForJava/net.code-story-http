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
package net.codestory.http;

import java.util.*;

public class UriParser {
  private final String[] patternParts;

  public UriParser(String uriPattern) {
    this.patternParts = parts(uriPattern);
  }

  public String[] params(String uri) {
    String[] uriParts = parts(uri);

    List<String> result = new ArrayList<>(); // TODO : optimize

    for (int i = 0; i < patternParts.length; i++) {
      String patternPart = patternParts[i];

      if (patternPart.startsWith(":")) {
        result.add(uriParts[i]);
      }
    }

    return result.toArray(new String[result.size()]);
  }

  public boolean matches(String uri) {
    String[] uriParts = parts(uri);
    if (patternParts.length != uriParts.length) {
      return false;
    }

    for (int i = 0; i < patternParts.length; i++) {
      String patternPart = patternParts[i];

      if (!patternPart.startsWith(":") && !patternPart.equals(uriParts[i])) {
        return false;
      }
    }

    return true;
  }

  static String[] parts(String uri) {
    return uri.split("/", -1);
  }
}
