/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2019 Basis Technology Corp.
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
package org.sleuthkit.autopsy.configurelogicalimager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.strip;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.corecomponents.TextPrompt;

/**
 * Edit rule panel
 */
public class EditRulePanel extends JPanel {

    private EditFullPathsRulePanel editFullPathsRulePanel = null;
    private EditNonFullPathsRulePanel editNonFullPathsRulePanel = null;

    /**
     * Creates new form EditRulePanel
     */
    public EditRulePanel(JButton okButton, JButton cancelButton, String ruleName, LogicalImagerRule rule) {
        if (rule.getFullPaths() != null && rule.getFullPaths().size() > 0) {
            editFullPathsRulePanel = new EditFullPathsRulePanel(okButton, cancelButton, ruleName, rule, true);
        } else {
            editNonFullPathsRulePanel = new EditNonFullPathsRulePanel(okButton, cancelButton, ruleName, rule, true);
        }
    }

    JPanel getPanel() {
        if (editFullPathsRulePanel != null) {
            return editFullPathsRulePanel;
        } else {
            return editNonFullPathsRulePanel;
        }
    }

    ImmutablePair<String, LogicalImagerRule> toRule() throws IOException, NumberFormatException {
        ImmutablePair<String, LogicalImagerRule> ruleMap;
        if (editFullPathsRulePanel != null) {
            ruleMap = editFullPathsRulePanel.toRule();
        } else {
            ruleMap = editNonFullPathsRulePanel.toRule();
       }
        return ruleMap;
    }
    
    static void setTextFieldPrompts(JTextComponent textField, String text) {
        /**
         * Add text prompt to the text field.
         */
        TextPrompt textPrompt;
        if (textField instanceof JTextArea) {
            textPrompt = new TextPrompt(text, textField, BorderLayout.NORTH);
        } else {
            textPrompt = new TextPrompt(text, textField);            
        }
        
        /**
         * Sets the foreground color and transparency of the text prompt.
         */
        textPrompt.setForeground(Color.LIGHT_GRAY);
        textPrompt.changeAlpha(0.9f); // Mostly opaque
    }
    
    @NbBundle.Messages({
        "EditRulePanel.validateRuleNameExceptionMsg=Rule name cannot be empty"
    })
    static public String validRuleName(String name) throws IOException {
        if (name.isEmpty()) {
            throw new IOException(Bundle.EditRulePanel_validateRuleNameExceptionMsg());
        }
        return name;
    }
    
    @NbBundle.Messages({
        "# ({0} - fieldName",
        "EditRulePanel.blankLineException={0} cannot have a blank line",
    })
    static public List<String> validateTextList(JTextArea textArea, String fieldName) throws IOException {
        List<String> list = new ArrayList<>();
        if (isBlank(textArea.getText())) {
            return null;
        }
        for (String line : textArea.getText().split("\\n")) { // NON-NLS
            line = strip(line);
            if (line.isEmpty()) {
                throw new IOException(Bundle.EditRulePanel_blankLineException(fieldName));
            }
            list.add(line);
        }
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

}
