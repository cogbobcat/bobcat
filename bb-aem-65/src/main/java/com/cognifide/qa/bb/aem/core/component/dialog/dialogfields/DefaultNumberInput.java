/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2019 Wunderman Thompson Technology
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
package com.cognifide.qa.bb.aem.core.component.dialog.dialogfields;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;

/**
 * Default implementation of {@link NumberInput}
 */
@PageObject(css = "coral-numberinput")
public class DefaultNumberInput implements NumberInput {

  @FindBy(css = "input")
  private WebElement input;

  @FindBy(xpath = Locators.ALTERNATE_LABEL_XPATH)
  private List<WebElement> label;

  @Override
  public void setValue(Object value) {
    input.clear();
    input.sendKeys(String.valueOf(value));
  }

  @Override
  public String getLabel() {
    return label.isEmpty() ? "" : label.get(0).getText();
  }
}
