package com.example.kson.lib_net.network.rx.download;

/**
 */

public class DownloadEntity
{
    private DownLoadListener downLoadListener;
    private String fileName;

    public DownloadEntity(DownLoadListener listener, String fileName)
    {
        this.downLoadListener = listener;
        this.fileName = fileName;
    }

    public DownLoadListener getDownLoadListener()
    {
        return downLoadListener;
    }

    public void setDownLoadListener(DownLoadListener downLoadListener)
    {
        this.downLoadListener = downLoadListener;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
}
