package com.b.nsofronovic.bitcoinwallet.rx

class RxEvent {
    data class DownloadPercentage(var double: Double)
    data class DownloadStatus(var isFinished: Boolean)
}