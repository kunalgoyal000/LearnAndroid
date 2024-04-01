package com.kunal.learnandroid.audioRecording.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}