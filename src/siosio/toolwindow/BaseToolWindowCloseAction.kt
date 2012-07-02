package siosio.toolwindow

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ex.ToolWindowManagerEx

public abstract class BaseToolWindowCloseAction(val position: ToolWindowAnchor): AnAction() {

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

  public override fun update(e: AnActionEvent?) {
    val project = PlatformDataKeys.PROJECT.getData(e!!.getDataContext());
    val toolWindowManager = ToolWindowManagerEx.getInstanceEx(project)!!

    e!!.getPresentation().setEnabled(
        toolWindowManager.getToolWindowIds()!!.any {
          val toolWindow = toolWindowManager.getToolWindow(it)!!
          toolWindow.isVisible() && toolWindow.getAnchor() == position
        }
    )
  }

}

public class BottomToolWindowCloseAction(): BaseToolWindowCloseAction(ToolWindowAnchor.BOTTOM) {
}

public class RightToolWindowCloseAction(): BaseToolWindowCloseAction(ToolWindowAnchor.RIGHT) {
}

public class LeftToolWindowCloseAction(): BaseToolWindowCloseAction(ToolWindowAnchor.LEFT) {
}


