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
package com.cognifide.qa.bb.aem.core.component.configuration;

import java.util.List;

/**
 * This class represents multifield entry with multiple fields configurations.
 */
public class MultifieldEntry {
  private List<FieldConfig> item;

  /**
   * @return list of FieldConfigs
   */
  public List<FieldConfig> getItem() {
    return item;
  }

  public void setItem(List<FieldConfig> item) {
    this.item = item;
  }

  @Override
  public String toString() {
    return "multifield item:\n" + item;
  }
}
