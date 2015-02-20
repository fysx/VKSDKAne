package ru.fysx.vksdkane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;

public class VKSDKProjector implements FREExtension {
	private VKSDKContext _cntx;
	@Override
	public FREContext createContext(String arg0) {
		_cntx = new VKSDKContext();
		//VKUIHelper.onResume(_cntx.getActivity()); 
		return _cntx;
	}

	@Override
	public void dispose() {
		VKUIHelper.onDestroy(_cntx.getActivity()); 

	}

	@Override
	public void initialize() {

	}

}
