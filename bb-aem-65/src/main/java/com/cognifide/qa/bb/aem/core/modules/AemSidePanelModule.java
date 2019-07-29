/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2018 Cognifide Ltd.
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
package com.cognifide.qa.bb.aem.core.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.qa.bb.aem.core.sidepanel.internal.SidePanel;
import com.cognifide.qa.bb.aem.core.sidepanel.internal.SidePanelImpl;
import com.cognifide.qa.bb.aem.core.sidepanel.internal.SidePanelTabBar;
import com.cognifide.qa.bb.aem.core.sidepanel.internal.SidePanelTabBarImpl;
import com.google.inject.AbstractModule;

/**
 * Contains binding related to AEM's Side Panel
 */
public class AemSidePanelModule extends AbstractModule {
  private static final Logger LOG = LoggerFactory.getLogger(AemSidePanelModule.class);

  @Override
  protected void configure() {
    LOG.debug("Configuring Bobcat module: {}", getClass().getSimpleName());

    bind(SidePanel.class).to(SidePanelImpl.class);
    bind(SidePanelTabBar.class).to(SidePanelTabBarImpl.class);
  }
}
