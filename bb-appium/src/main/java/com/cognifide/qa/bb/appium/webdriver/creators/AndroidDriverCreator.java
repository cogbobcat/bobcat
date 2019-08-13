/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2019 Cognifide Ltd.
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
package com.cognifide.qa.bb.appium.webdriver.creators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.qa.bb.constants.ConfigKeys;
import com.cognifide.qa.bb.provider.selenium.webdriver.creators.WebDriverCreator;
import com.google.inject.Inject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;

/**
 * Creates instances of {@link AndroidDriver}
 */
public class AndroidDriverCreator implements WebDriverCreator {

  private static final Logger LOG = LoggerFactory.getLogger(AndroidDriverCreator.class);
  private static final String ID = "android";

  @Inject
  private Properties properties;

  @Override
  public WebDriver create(Capabilities capabilities) {
    LOG.info("Starting the initialization of '{}' WebDriver instance", ID);
    LOG.debug("Initializing WebDriver with following capabilities: {}", capabilities);
    return createMobileDriver(capabilities, properties);
  }

  @Override
  public String getId() {
    return ID;
  }

  private WebDriver createMobileDriver(Capabilities capabilities, Properties properties) {
    final URL url;
    try {
      url = new URL(properties.getProperty(ConfigKeys.WEBDRIVER_APPIUM_URL));
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Your Appium URL seems to be malformed", e);
    }

    final String platform = properties.getProperty(ConfigKeys.WEBDRIVER_CAP_PLATFORM_NAME);
    if (MobilePlatform.ANDROID.equals(platform)) {
      return new AndroidDriver(url, capabilities);
    } else {
      throw new IllegalArgumentException(String.format(
          "Requested Android driver but incorrect platform name was provided. %s should be set to: %s",
          ConfigKeys.WEBDRIVER_CAP_PLATFORM_NAME, MobilePlatform.ANDROID));
    }
  }
}
