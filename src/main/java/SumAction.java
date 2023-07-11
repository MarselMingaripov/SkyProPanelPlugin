import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class SumAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        //Messages.showMessageDialog("Ready", "Summator", Messages.getInformationIcon());
        SkyProPanel skyPanel = new SkyProPanel();
        skyPanel.show();
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
