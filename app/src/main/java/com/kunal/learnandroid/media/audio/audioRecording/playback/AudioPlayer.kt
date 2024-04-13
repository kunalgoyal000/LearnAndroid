package com.kunal.learnandroid.media.audio.audioRecording.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}