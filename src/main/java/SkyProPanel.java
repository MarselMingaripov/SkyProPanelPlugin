import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkyProPanel extends DialogWrapper {
    private JTextField value1Field;
    private JTextField value2Field;
    private JLabel resultLabel;
    private JButton getResult;
    private JButton getTree;
    private JButton getSocket;

    protected SkyProPanel() {
        super(true);
        setTitle("SkyPanel");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        value1Field = new JTextField();
        value2Field = new JTextField();
        resultLabel = new JLabel();

        getResult = new JButton("get value");
        getResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value1 = Integer.parseInt(value1Field.getText());
                int value2 = Integer.parseInt(value2Field.getText());
                int sum = value1 + value2;
                resultLabel.setText(String.valueOf(sum));
            }
        });
        getTree = new JButton("get tree");
        getSocket = new JButton("get socket");

        panel.add(new JLabel("Value 1:"));
        panel.add(value1Field);
        panel.add(new JLabel("Value 2:"));
        panel.add(value2Field);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Result:"));
        panel.add(resultLabel);

        panel.add(getResult);
        panel.add(getTree);
        panel.add(getSocket);

        return panel;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
    }
}
