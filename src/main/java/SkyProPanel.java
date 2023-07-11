import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.*;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SkyProPanel implements ToolWindowFactory{

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        PanelContent panelContent = new PanelContent(project);
        Content content = ContentFactory.SERVICE.getInstance().createContent(panelContent.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }


    private static class PanelContent {

        private final JPanel skyProPanel = new JPanel();
        private final JBTextField value1Field = new JBTextField();
        private final JBTextField value2Field = new JBTextField();
        private final JBTextArea resultLabel = new JBTextArea();
        private final JButton getValueButton = new JButton("get value");
        private final JButton getTree = new JButton("get tree");
        private final JButton getSocket = new JButton("get socket");
        private final Project project;


        public PanelContent(Project project) {
            skyProPanel.setLayout(new BorderLayout(0, 20));
            skyProPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
            skyProPanel.add(createSkyProPanel(), BorderLayout.PAGE_START);
            this.project = project;
        }

        @NotNull
        private JPanel createSkyProPanel() {
            JPanel myPanel = new JPanel();
            myPanel.setLayout(new GridLayout(0, 1));
            myPanel.add(new JLabel("Value 1:"));
            myPanel.add(value1Field);
            myPanel.add(new JLabel("Value 2:"));
            myPanel.add(value2Field);
            myPanel.add(new JLabel("Result: "));
            myPanel.add(resultLabel);
            resultLabel.setEditable(false);
            myPanel.add(getValueButton);
            myPanel.add(getTree);
            myPanel.add(getSocket);
            getValueButton.addActionListener(e ->

            {
                int value1 = Integer.parseInt(value1Field.getText());
                int value2 = Integer.parseInt(value2Field.getText());
                int sum = value1 + value2;
                resultLabel.setText(value1Field.getText() + " + " + value2Field.getText() + " = " + sum);

            });

            getTree.addActionListener(e -> {
                ProjectsDirUtil projectsDirUtil = new ProjectsDirUtil();
                Messages.showInfoMessage(projectsDirUtil.getProjectTreeString(), "Project Properties");
            });

            return myPanel;
        }

        public JPanel getContentPanel() {
            return skyProPanel;
        }
    }
}
