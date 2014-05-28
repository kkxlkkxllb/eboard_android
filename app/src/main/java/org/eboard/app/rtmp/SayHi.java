
package org.eboard.app.rtmp;

public class SayHi {
	public native void Init();

	public native void OpenPublisher(String rtmpUrl);

	public native void ClosePublisher();

	public native void OpenPlayer(String rtmpUrl);

	public native void ClosePlayer();

	public native void Deinit();

	public OnEventCallback mOnEventCallback;

	private void onEventCallback(int event) {
		System.out.println("onEventCallback: " + event);
		if (mOnEventCallback != null) {
			mOnEventCallback.onEvent(event);
		}
	}

	static {
		System.loadLibrary("sayhi");
	}

	public void setOnEventCallback(OnEventCallback onEventCallback) {
		mOnEventCallback = onEventCallback;
	}

	public interface OnEventCallback {
		public void onEvent(int event);
	}
}
