/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vividus.ui.web.storage;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.vividus.ui.web.action.WebJavascriptActions;

class JavascriptStorage implements LocalStorage, SessionStorage
{
    private final StorageType storageType;
    private final WebJavascriptActions javascriptActions;

    JavascriptStorage(StorageType storageType, WebJavascriptActions javascriptActions)
    {
        this.storageType = storageType;
        this.javascriptActions = javascriptActions;
    }

    @Override
    public String getItem(String key)
    {
        return executeScript("return window.%s.getItem(arguments[0])", key);
    }

    @Override
    public Set<String> keySet()
    {
        return new HashSet<>(executeScript("return Object.keys(window.%s)"));
    }

    @Override
    public void setItem(String key, String value)
    {
        executeScript("window.%s.setItem(arguments[0], arguments[1])", key, value);
    }

    @Override
    public String removeItem(String key)
    {
        return executeScript("window.%s.removeItem(arguments[0])", key);
    }

    @Override
    public void clear()
    {
        executeScript("window.%s.clear()");
    }

    @Override
    public int size()
    {
        return executeScript("return window.%s.length");
    }

    private <T> T executeScript(String format, Object... args)
    {
        return javascriptActions.executeScript(String.format(format, storageType.getJavascriptPropertyName()), args);
    }
}
