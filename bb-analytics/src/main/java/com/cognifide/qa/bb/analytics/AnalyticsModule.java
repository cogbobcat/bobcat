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
package com.cognifide.qa.bb.analytics;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Module containing bindings for the analytics solutions
 */
public class AnalyticsModule extends AbstractModule {

  @Override
  protected void configure() {

    bind(Analytics.class)
        .annotatedWith(Names.named("AdobeAnalytics"))
        .toProvider(AdobeAnalyticsProvider.class);
    bind(Analytics.class)
        .annotatedWith(Names.named("GoogleAnalytics"))
        .toProvider(GoogleAnalyticsProvider.class);
  }
}
