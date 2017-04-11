/*
 * Copyright 2016 Cognifide Ltd..
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cognifide.qa.bb.loadable.hierarchy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.cognifide.qa.bb.exceptions.BobcatRuntimeException;
import com.cognifide.qa.bb.loadable.LoadableProcessorFilter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.qa.bb.loadable.annotation.LoadableComponent;
import com.cognifide.qa.bb.loadable.context.ClassFieldContext;
import com.cognifide.qa.bb.loadable.context.ConditionContext;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.utils.AopUtil;
import com.cognifide.qa.bb.webelement.BobcatWebElement;
import com.google.inject.Inject;

/**
 * This intercepts invocation of {@link WebElement} methods and runs evaluation of conditions
 * provided in the {@link LoadableComponent} annotations on field that called the {@link WebElement}
 * method and every field in the hierarchy above.
 *
 */
public class WebElementInterceptor implements MethodInterceptor {

  private static final int ORIGINAL_CALLER_CLASS_LEVEL = 6;

  private static final Logger LOG = LoggerFactory.getLogger(WebElementInterceptor.class);

  @Inject
  private ConditionsExplorer loadableCondsExplorer;

  @Inject
  private ConditionChainRunner loadConditionChainRunner;

  @Inject
  private PageObjectInvocationTracker pageObjectInvocationTracker;

  @Inject
  private Set<LoadableProcessorFilter> loadableProcessorFilterSet;

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    Class methodCallerClass = getMethodCallerClassWithoutGuiceContext();

    if (isApplicable(methodCallerClass)) {

      LOG.debug(
          "Caught invocation of method {} from {}. Started processing loadable component conditions hierarchy",
          methodInvocation.getMethod().getName(), methodCallerClass.getName());

      BobcatWebElement caller = (BobcatWebElement) methodInvocation.getThis();
      ClassFieldContext directContext = acquireDirectContext(caller);
      ConditionStack loadableContextHierarchy =
          loadableCondsExplorer.discoverLoadableContextHierarchy(directContext,
              pageObjectInvocationTracker.getSubjectStack());

      loadConditionChainRunner.chainCheck(loadableContextHierarchy);
    }
    return methodInvocation.proceed();

  }

  private Class getMethodCallerClassWithoutGuiceContext() {
    try {
      return AopUtil.getBaseClassForAopObject(Class.forName(
          Thread.currentThread().getStackTrace()[ORIGINAL_CALLER_CLASS_LEVEL].getClassName()));
    } catch (ClassNotFoundException e) {
      throw new BobcatRuntimeException("Class calling web element not found!", e);
    }
  }

  private ClassFieldContext acquireDirectContext(BobcatWebElement caller) {
    List<ConditionContext> directLoadCondition = caller.getLoadableConditionContext();
    ClassFieldContext directContext;
    if (!directLoadCondition.isEmpty()) {
      directContext = new ClassFieldContext(caller, caller.getLoadableConditionContext());
    } else {
      directContext = new ClassFieldContext(caller, Collections.emptyList());
    }
    return directContext;
  }

  private boolean isApplicable(Class clazz) {
    if (clazz.isAnnotationPresent(PageObject.class)) {
      return Arrays.stream(Thread.currentThread().getStackTrace())
          .map(StackTraceElement::getClassName).map(className -> {
            try {
              return Class.forName(className);
            } catch (ClassNotFoundException e) {
              LOG.error("Caller class from the stacktrace not found!", e);
            }
            return null;
          }).anyMatch(clazzAbove -> loadableProcessorFilterSet.stream()
              .anyMatch(filter -> filter.isApplicable(clazzAbove)));
    } else {
      return loadableProcessorFilterSet.stream().anyMatch(filter -> filter.isApplicable(clazz));
    }
  }
}
