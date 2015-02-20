package ru.fysx.vksdkane.projectedMethods;

import ru.fysx.vksdkane.events.VKEvent;
import android.util.Log;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;

public class Authorize implements FREFunction {

	@Override
	public FREObject call(FREContext cntx, FREObject[] parameters) {
		final FREContext _cntx = cntx;
		String[] scope;
		if(parameters.length>2)
			scope = new String[parameters.length-2];
		else scope = new String[]{VKScope.FRIENDS};

		for(int i=2;i<parameters.length;i++){
			try {
				scope[i-2] = parameters[i].getAsString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		Boolean revoke = false; 
		Boolean forceOAuth = false; 
		
		
		try {
			revoke = parameters[0].getAsBool();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			forceOAuth = parameters[1].getAsBool();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		VKSdk.authorize(scope, revoke, forceOAuth);
		return null;
	}

}
