/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;

/**
 * Default implementation of {@link Select}
 */
@PageObject(xpath = "//coral-select/..")
public class DefaultSelect implements Select {

  private static final String SELECT_OPTIONS_CSS = ".coral3-SelectList-item";

  @FindBy(css = ".coral3-Select")
  private WebElement selectField;

  @FindBy(css = Locators.LABEL_CSS)
  private List<WebElement> label;

  @Override
  public void setValue(Object value) {
    selectField.click();
    List<WebElement> options = selectField.findElements(By.cssSelector(SELECT_OPTIONS_CSS));
    options.stream()
        .filter(o -> value.toString().equals(o.getText()))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            String.format("Option with text %s not found", value.toString())))
        .click();
  }

  @Override
  public String getLabel() {
    return label.isEmpty() ? "" : label.get(0).getText();
  }
}
