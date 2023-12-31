import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SkyProPanel implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        PanelContent panelContent = new PanelContent();
        Content content = ContentFactory.getInstance().createContent(panelContent.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }


    private static class PanelContent {

        private final JPanel skyProPanel = new JPanel();
        private final JBTextField value1Field = new JBTextField();
        private final JBTextField value2Field = new JBTextField();
        private final JBTextArea resultLabel = new JBTextArea();
        private final JBScrollPane scrollPane = new JBScrollPane(resultLabel);
        private final JButton getValueButton = new JButton("get value");
        private final JButton getTreeButton = new JButton("get tree");
        private final JButton getSocketButton = new JButton("get socket");


        public PanelContent() {
            skyProPanel.setLayout(new GridLayout(0, 1));
            skyProPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
            skyProPanel.add(createSkyProPanel(), BorderLayout.PAGE_START);
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
            //myPanel.add(resultLabel);
            myPanel.add(scrollPane);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            resultLabel.setEditable(false);

            myPanel.add(getValueButton);
            myPanel.add(getTreeButton);
            myPanel.add(getSocketButton);

            getValueButton.addActionListener(e -> {
                if (isNumeric(value1Field.getText()) && isNumeric(value2Field.getText())) {
                    int value1 = Integer.parseInt(value1Field.getText());
                    int value2 = Integer.parseInt(value2Field.getText());
                    int sum = value1 + value2;
                    resultLabel.setText(value1Field.getText() + " + " + value2Field.getText() + " = " + sum);
                } else {
                    resultLabel.setText("Please, try again. We need some digits...");
                }

            });

            getTreeButton.addActionListener(e -> {
                ProjectsDirUtil projectsDirUtil = new ProjectsDirUtil();
                //Messages.showInfoMessage(projectsDirUtil.getProjectTreeString(), "Project Properties");
                resultLabel.setText(projectsDirUtil.getProjectTreeString());
            });

            getSocketButton.addActionListener(e -> {
                ProjectConnectionUtil projectConnectionUtil = new ProjectConnectionUtil();;
                projectConnectionUtil.createConnection(resultLabel);
            });

            return myPanel;
        }

        public JPanel getContentPanel() {
            return skyProPanel;
        }

        private static boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
