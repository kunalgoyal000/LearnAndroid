package com.kunal.learnandroid.media.audio.audioRecording.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}