/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.ide.ui;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.ide.ui.search.BooleanOptionDescription;
import com.intellij.openapi.application.ex.ApplicationInfoEx;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Konstantin Bulenkov
 */
public class PluginOptionsTopHitProvider extends OptionsTopHitProvider {
  @NotNull
  @Override
  public Collection<BooleanOptionDescription> getOptions(@Nullable Project project) {
    ArrayList<BooleanOptionDescription> options = new ArrayList<>();
    ApplicationInfoEx applicationInfo = ApplicationInfoEx.getInstanceEx();
    for (IdeaPluginDescriptor pluginDescriptor : PluginManagerCore.getPlugins()) {
      if (applicationInfo.isEssentialPlugin(pluginDescriptor.getPluginId().getIdString())) {
        continue;
      }

      options.add(new PluginBooleanOptionDescriptor(pluginDescriptor.getPluginId()));

    }
    return options;
  }

  @Override
  public String getId() {
    return "plugins";
  }
}
