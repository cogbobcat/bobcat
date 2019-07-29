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
package com.cognifide.qa.bb.aem64sp2.core.modules;

import com.cognifide.qa.bb.aem.core.modules.AemConfigModule;
import com.cognifide.qa.bb.aem.core.modules.AemCoreModule;
import com.cognifide.qa.bb.aem.core.modules.AemFieldsModule;
import com.cognifide.qa.bb.aem.core.modules.AemLoginModule;
import com.cognifide.qa.bb.aem.core.modules.AemPageModule;
import com.cognifide.qa.bb.aem.core.modules.AemSidePanelModule;
import com.cognifide.qa.bb.aem.core.modules.AemSitesAdminModule;
import com.cognifide.qa.bb.aem.core.modules.SlingPageActionsModule;
import com.google.inject.AbstractModule;

/**
 * Main module that needs to be installed to use AEM 6.4 functions.
 * <p>
 * It install all sub-modules related to AEM 6.4.
 */
public class Aem64SP2FullModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new AemCoreModule());
    install(new AemLoginModule());
    install(new AemSitesAdminModule());
    install(new SlingPageActionsModule());
    install(new AemComponentModule());
    install(new AemSidePanelModule());
    install(new AemPageModule());
    install(new AemFieldsModule());
    install(new AemConfigModule());
  }

}
