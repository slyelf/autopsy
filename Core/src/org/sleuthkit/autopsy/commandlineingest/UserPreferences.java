/*
 * Autopsy Forensic Browser
 *
 * Copyright 2019-2019 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.commandlineingest;

import org.openide.util.NbPreferences;

/**
 * Provides convenient access to a UserPreferences node for user preferences
 * with default values.
 */
public final class UserPreferences {

    private static final java.util.prefs.Preferences preferences = NbPreferences.forModule(UserPreferences.class);
    private static final String COMMAND_LINE_MODE_RESULTS_FOLDER = "CommandLineModeResultsFolder"; // NON-NLS
    private static final String COMMAND_LINE_MODE_CONTEXT_STRING = "CommandLineModeContext"; // NON-NLS

    // Prevent instantiation.
    private UserPreferences() {
    }

    /**
     * Get results folder for command line mode from persistent storage.
     *
     * @return String Selected output folder.
     */
    public static String getCommandLineModeResultsFolder() {
        return preferences.get(COMMAND_LINE_MODE_RESULTS_FOLDER, "");
    }

    /**
     * Set results folder for command line mode from persistent storage.
     *
     * @param folder Selected output folder.
     */
    public static void setCommandLineModeResultsFolder(String folder) {
        preferences.put(COMMAND_LINE_MODE_RESULTS_FOLDER, folder);
    }

    /**
     * Get context string for command line mode ingest module settings.
     *
     * @return String Context string for command line mode ingest module
     * settings.
     */
    public static String getCommandLineModeIngestModuleContextString() {
        return COMMAND_LINE_MODE_CONTEXT_STRING;
    }
}
