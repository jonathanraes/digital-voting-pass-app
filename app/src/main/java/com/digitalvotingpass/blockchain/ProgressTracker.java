package com.digitalvotingpass.blockchain;

import android.util.Log;

import org.bitcoinj.core.Peer;
import org.bitcoinj.core.listeners.DownloadProgressTracker;

import java.util.Date;

class ProgressTracker extends DownloadProgressTracker {

    BlockchainCallBackListener listener;

    ProgressTracker(BlockchainCallBackListener listener) {
        this.listener = listener;
    }

    @Override
    protected void progress(double pct, int blocksSoFar, Date date) {
        listener.onDownloadProgress(pct, blocksSoFar, date);
    }

    @Override
    public void await() throws InterruptedException {
        super.await();
        Log.e("Waiting", "await");
    }

    @Override
    protected void startDownload(int blocks) {
        super.startDownload(blocks);
        listener.onInitComplete();
    }

    @Override
    protected void doneDownload() {
        super.doneDownload();
        listener.onDownloadComplete();
    }
}
