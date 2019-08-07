package com.th.windows.robot.util

import com.sun.jna.platform.win32.WinUser.INPUT
import com.sun.jna.platform.win32.WinDef.DWORD
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef.WORD
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR


object Keyboard {
    private const val KEYEVENTF_KEYDOWN = 0
    private const val KEYEVENTF_KEYUP = 2

    /**
     * Check if a key is pressed.
     *
     * @param vkCode
     * Key-code. For example: *KeyEvent.VK_SHIFT *
     *
     * @return `true` if key is down. False otherwise.
     */
    fun isKeyDown(vkCode: Int): Boolean {
        val state = User32.INSTANCE.GetAsyncKeyState(vkCode)
        // check most-significant bit for non-zero.
        return 0x1 and (state.toInt() shr (java.lang.Short.SIZE - 1)) != 0
    }

    /**
     * Sends a key-down input followed by a key-up input for the given character
     * value c.
     *
     * @param c
     */
    fun pressKey(c: Int) {
        val input = INPUT()
        input.type = DWORD(INPUT.INPUT_KEYBOARD.toLong())
        input.input.setType("ki")
        input.input.ki.wScan = WORD(0)
        input.input.ki.time = DWORD(0)
        input.input.ki.dwExtraInfo = ULONG_PTR(0)
        input.input.ki.wVk = WORD(c.toLong())
        input.input.ki.dwFlags = DWORD(KEYEVENTF_KEYDOWN.toLong())
        User32.INSTANCE.SendInput(DWORD(1), input.toArray(1) as Array<INPUT>, input.size())
        input.input.ki.wVk = WORD(c.toLong())
        input.input.ki.dwFlags = DWORD(KEYEVENTF_KEYUP.toLong())
        User32.INSTANCE.SendInput(DWORD(1), input.toArray(1) as Array<INPUT>, input.size())
    }

    /**
     * Sends a key-down input for the given character value c.
     *
     * @param c
     */
    fun sendKeyDown(c: Int) {
        val input = INPUT()
        input.type = DWORD(INPUT.INPUT_KEYBOARD.toLong())
        input.input.setType("ki")
        input.input.ki.wScan = WORD(0)
        input.input.ki.time = DWORD(0)
        input.input.ki.dwExtraInfo = ULONG_PTR(0)
        input.input.ki.wVk = WORD(c.toLong())
        input.input.ki.dwFlags = DWORD(KEYEVENTF_KEYDOWN.toLong())
        User32.INSTANCE.SendInput(DWORD(1), input.toArray(1) as Array<INPUT>, input.size())
    }

    /**
     * Sends a key-up input for the given character value c.
     *
     * @param c
     */
    fun sendKeyUp(c: Int) {
        val input = INPUT()
        input.type = DWORD(INPUT.INPUT_KEYBOARD.toLong())
        input.input.setType("ki")
        input.input.ki.wScan = WORD(0)
        input.input.ki.time = DWORD(0)
        input.input.ki.dwExtraInfo = ULONG_PTR(0)
        input.input.ki.wVk = WORD(c.toLong())
        input.input.ki.dwFlags = DWORD(KEYEVENTF_KEYUP.toLong())
        User32.INSTANCE.SendInput(DWORD(1), input.toArray(1) as Array<INPUT>, input.size())
    }
}