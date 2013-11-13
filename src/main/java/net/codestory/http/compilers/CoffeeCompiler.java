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

import static java.nio.charset.StandardCharsets.*;

import java.io.*;

import javax.script.*;

class CoffeeCompiler {
  private final Bindings CONTEXT;
  private final CompiledScript COFFEE_JS;

  public CoffeeCompiler() {
    ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");

    try (Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream("coffee/coffee-script.js"), UTF_8)) {
      COFFEE_JS = ((Compilable) nashorn).compile(reader);
      CONTEXT = nashorn.getBindings(ScriptContext.ENGINE_SCOPE);
    } catch (IOException | ScriptException e) {
      throw new IllegalStateException(e);
    }
  }

  public synchronized String compile(String source) throws IOException {
    try {
      CONTEXT.put("coffeeScriptSource", source);
      return COFFEE_JS.eval(CONTEXT).toString();
    } catch (ScriptException e) {
      throw new IOException("Unable to compile coffee", e);
    }
  }
}