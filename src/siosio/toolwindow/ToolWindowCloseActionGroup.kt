package siosio.toolwindow

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ex.ToolWindowManagerEx

public class ToolWindowCloseActionGroup(): ActionGroup("Close Tool Window", true) {
  public override fun getChildren(p0: AnActionEvent?): Array<AnAction?> {
    return array(
        ToolWindowCloseAction(ToolWindowAnchor.BOTTOM, "Close bottom Tool Window"),
        ToolWindowCloseAction(ToolWindowAnchor.RIGHT, "Close right Tool Window"),
        ToolWindowCloseAction(ToolWindowAnchor.LEFT, "Close left Tool Window")
    )
  }

  public override fun update(e: AnActionEvent?) {
    val project = PlatformDataKeys.PROJECT.getData(e!!.getDataContext());
    val toolWindowManager = ToolWindowManagerEx.getInstanceEx(project)!!

    e!!.getPresentation().setEnabled(
        toolWindowManager.getToolWindowIds()!!.any {
          toolWindowManager.getToolWindow(it)!!.isVisible()
        }
    )
  }
}
