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
package com.cognifide.qa.bb.scope.webelement;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.cognifide.qa.bb.mapper.annotations.FieldAnnotationsProvider;
import com.cognifide.qa.bb.scope.ParentElementLocatorProvider;
import com.cognifide.qa.bb.utils.AnnotationsHelper;
import com.google.inject.Injector;

/**
 * Locator factory where the scope is defined by the provided webElement.
 */
public class WebElementScopedLocatorFactory
    implements ElementLocatorFactory, ParentElementLocatorProvider {

  private final WebDriver webDriver;

  private final WebElement webElement;

  private final Injector injector;

  /**
   * Constructs WebElementScopedLocatorFactory.
   *
   * @param webDriver  WebDriver instance.
   * @param webElement Defines scope for the objects that this factory is going to produce.
   */
  public WebElementScopedLocatorFactory(WebDriver webDriver, WebElement webElement,
      Injector injector) {
    this.webDriver = webDriver;
    this.webElement = webElement;
    this.injector = injector;
  }

  /**
   * Returns scope represented by this locator factory.
   */
  @Override
  public ElementLocator getCurrentScope() {
    return new WebElementLocator(webElement);
  }

  /**
   * Return a DefaultElementLocator.
   */
  @Override
  public ElementLocator createLocator(Field field) {
    return new DefaultElementLocator(resolveContext(field),
        FieldAnnotationsProvider.create(field, injector));
  }

  private SearchContext resolveContext(Field field) {
    return AnnotationsHelper.isGlobal(field) ? webDriver : webElement;
  }

}
