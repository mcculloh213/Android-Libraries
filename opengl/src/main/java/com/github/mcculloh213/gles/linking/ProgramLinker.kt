package com.github.mcculloh213.gles.linking

import android.opengl.GLES30

class ProgramLinker private constructor(val id: Int) {
    private val handles: MutableMap<String, Int> = hashMapOf()
    val vertexShader: Int
        get() = get(KEY_VERTEX_SHADER)
    val fragmentShader: Int
        get() = get(KEY_FRAGMENT_SHADER)
    operator fun get(handle: String): Int = handles[handle] ?: 0
    operator fun set(handle: String, value: Int) {
        handles[handle] = value
    }
    fun linkVertexShader(type: Int, shader: String) {
        val h = loadShader(type, shader)
        attachShader(h)
        set(KEY_VERTEX_SHADER, h)
    }
    fun linkFragmentShader(type: Int, shader: String) {
        val h = loadShader(type, shader)
        attachShader(h)
        set(KEY_FRAGMENT_SHADER, h)
    }
    fun getAttribLocation(location: String) = GLES30.glGetAttribLocation(id, location)
    fun getUniformLocation(location: String) = GLES30.glGetUniformLocation(id, location)
    private fun loadShader(type: Int, src: String): Int {
        val s = GLES30.glCreateShader(type)
        GLES30.glShaderSource(s, src)
        GLES30.glCompileShader(s)
        return s
    }
    private fun attachShader(handle: Int) = GLES30.glAttachShader(id, handle)

    companion object {
        const val KEY_VERTEX_SHADER = "vertexShader"
        const val KEY_FRAGMENT_SHADER = "fragmentShader"
        @JvmOverloads
        fun newProgram(vertexShader: String? = null, fragmentShader: String? = null): ProgramLinker {
            val l = ProgramLinker(GLES30.glCreateProgram())
            vertexShader?.let { l.linkVertexShader(GLES30.GL_VERTEX_SHADER, it) }
            fragmentShader?.let { l.linkFragmentShader(GLES30.GL_FRAGMENT_SHADER, it) }
            return l
        }
        fun checkError(operation: String) {
            var error: Int = 0
        }
    }
}