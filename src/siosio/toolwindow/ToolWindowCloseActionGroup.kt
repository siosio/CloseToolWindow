package siosio.toolwindow

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ex.ToolWindowManagerEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.wm.ToolWindowAnchor

public class ToolWindowCloseActionGroup() : ActionGroup("Close Tool Window", true) {
  public override fun getChildren(p0: AnActionEvent?): Array<AnAction?> {
    return array(
        ToolWindowCloseAction(ToolWindowAnchor.BOTTOM, "close bottom Tool Windows"),
        ToolWindowCloseAction(ToolWindowAnchor.RIGHT, "close right Tool Windows"),
        ToolWindowCloseAction(ToolWindowAnchor.LEFT, "close left Tool Windows")
        )
  }
}
