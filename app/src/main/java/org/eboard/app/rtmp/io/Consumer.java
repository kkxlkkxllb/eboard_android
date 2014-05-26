package org.eboard.app.rtmp.io;

public interface Consumer {
	
	public void putData(long ts, byte[] buf, int size);
	
	public void setRecording(boolean isRecording);
	
	public boolean isRecording();	
}
