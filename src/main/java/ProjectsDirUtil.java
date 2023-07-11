import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;

public class ProjectsDirUtil {

    public String getProjectTreeString() {
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        if (openProjects.length > 0) {
            Project project = openProjects[0];
            VirtualFile baseDir = project.getBaseDir();
            if (baseDir != null) {
                StringBuilder stringBuilder = new StringBuilder();
                traverseDirectory(baseDir, stringBuilder, 0);
                return stringBuilder.toString();
            }
        }
        return null;
    }

    private static void traverseDirectory(VirtualFile dir, StringBuilder stringBuilder, int depth) {
        String indent = getIndent(depth);
        stringBuilder.append(indent).append(dir.getName()).append("\n");
        for (VirtualFile child : dir.getChildren()) {
            if (child.isDirectory()) {
                traverseDirectory(child, stringBuilder, depth + 1);
            } else {
                stringBuilder.append(getIndent(depth + 1)).append(child.getName()).append("\n");
            }
        }
    }

    private static String getIndent(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("— ");
        }
        return stringBuilder.toString();
    }
}
