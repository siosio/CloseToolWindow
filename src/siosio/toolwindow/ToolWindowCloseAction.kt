package siosio.toolwindow

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ex.ToolWindowManagerEx

public class ToolWindowCloseAction(val position: ToolWindowAnchor, val text: String): AnAction(text) {

  public override fun actionPerformed(e: AnActionEvent?) {
    val project = PlatformDataKeys.PROJECT.getData(e!!.getDataContext());
    val toolWindowManager = ToolWindowManagerEx.getInstanceEx(project)!!

    val toolWindowIds = toolWindowManager.getToolWindowIds()!!.filter {
      toolWindowManager.getToolWindow(it)!!.isVisible()
    }
    toolWindowIds.forEach() {
      val toolWindow = toolWindowManager.getToolWindow(it)
      if (toolWindow?.getAnchor() == position) {
        toolWindowManager.hideToolWindow(it!!, true)
      }
    }
  }
}