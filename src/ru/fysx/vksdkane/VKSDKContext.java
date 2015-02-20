package ru.fysx.vksdkane;

import java.util.HashMap;
import java.util.Map;

import ru.fysx.vksdkane.projectedMethods.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.air.*;
import com.adobe.air.AndroidActivityWrapper.ActivityState;
import com.vk.sdk.VKUIHelper;

public class VKSDKContext extends FREContext implements ActivityResultCallback, StateChangeCallback  {
//	private 
    private AndroidActivityWrapper aaw;
	public VKSDKContext() {
        aaw = AndroidActivityWrapper.GetAndroidActivityWrapper();
        aaw.addActivityResultListener( this );
        aaw.addActivityStateChangeListner( this );
    }
	@Override
	public void dispose() {
		if (aaw!=null) {
            aaw.removeActivityResultListener( this );
            aaw.removeActivityStateChangeListner( this );
            aaw = null;
        }
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
        Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
        functionMap.put("initSDK", new InitSdk() );
        functionMap.put("authorize", new Authorize() );
        functionMap.put("getAccessToken", new GetAccessToken() );
        return functionMap;
	}
	@Override  
    public void onActivityResult(int requestCode, int resultCode, Intent intent ) { 
		VKUIHelper.onActivityResult(getActivity(), requestCode, resultCode, intent); 
    }
	@Override
	public void onActivityStateChanged(ActivityState state) {
		// TODO Auto-generated method stub
		switch ( state ) {
	        case STARTED:
	    		break;
	        case RESTARTED:
	    		break;
	        case RESUMED:
	    		VKUIHelper.onResume(getActivity()); 
	    		break;
	        case PAUSED:
	    		break;
	        case STOPPED:
	    		break;
	        case DESTROYED:
	    		VKUIHelper.onDestroy(getActivity()); 
	    		break;
	    }
	}

	@Override
	public void onConfigurationChanged(Configuration arg0) {
		// TODO Auto-generated method stub
		
	}
}
