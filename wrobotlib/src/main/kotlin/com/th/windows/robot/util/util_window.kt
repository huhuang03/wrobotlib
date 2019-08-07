package com.th.windows.robot.util

import com.sun.jna.Native
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef

fun winGetHandleByTitle(title: String): WinDef.HWND? {
    var rst: WinDef.HWND? = null
    var found = false
    User32.INSTANCE.EnumWindows({ hWnd, data ->
        if (!found) {
            val bufSize = 512
            val buffer = CharArray(0)
            User32.INSTANCE.GetWindowText(hWnd, buffer, bufSize)
            val winTitle = Native.toString(buffer)
            if (winTitle == title) {
                rst = hWnd
                found = true
            }
        }
        true
    }, null)
    return rst
}

fun winActive(winDef: WinDef.HWND) {
    User32.INSTANCE.SetForegroundWindow(winDef)
}

fun send(key: Int) {
    Keyboard.pressKey(key)
}
