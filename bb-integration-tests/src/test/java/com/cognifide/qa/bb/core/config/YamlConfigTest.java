/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Wunderman Thompson Technology
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.qa.bb.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.core.modules.TestModule;
import com.cognifide.qa.bb.junit5.BobcatExtension;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.google.inject.Inject;

@BobcatExtension
@Modules(TestModule.class)
public class YamlConfigTest {

  @Inject
  private Properties properties;

  @Test
  public void additionalContextsShouldBeLoadedWhenSelectedInConfig() {
    assertThat(properties)
        .contains(
            entry("property1", "value1"),
            entry("property2", "value2"),
            entry("prop3", "val3"),
            entry("prop4", "val4"))
        .doesNotContain(
            entry("prop1", "val1"),
            entry("prop2", "val2"),
            entry("property3", "value3"),
            entry("property4", "value4")
        );
  }
}
