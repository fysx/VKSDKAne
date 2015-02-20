package ru.fysx.vksdkane.projectedMethods;

import java.util.HashMap;
import java.util.Map;

import ru.fysx.vksdkane.VKSDKContext;
import ru.fysx.vksdkane.events.VKEvent;
import android.widget.Toast;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;

public class InitSdk implements FREFunction {
    private VKSDKContext _cntx;

	@Override
	public FREObject call(FREContext cntx, FREObject[] obj) {
		_cntx = (VKSDKContext)cntx;
		try {
    		VKUIHelper.onResume(cntx.getActivity()); 
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put(VKAccessToken.ACCESS_TOKEN, obj[1].getAsString());
			parameters.put(VKAccessToken.USER_ID, obj[2].getAsString());
			parameters.put(VKAccessToken.SECRET, obj[3].getAsString());
			parameters.put(VKAccessToken.EXPIRES_IN, obj[4].getAsInt()+"");
			VKSdkListener listener =  new VKSdkListener(){
				@Override
				public void onCaptchaError(VKError captchaError) {
					_cntx.dispatchStatusEventAsync(VKEvent.CAPTCHA_ERROR, "status");
				}
				@Override
				public void  onAcceptUserToken(VKAccessToken token){
					_cntx.dispatchStatusEventAsync(VKEvent.ACCEPT_TOKEN, "status");
				}
				@Override
				public void onTokenExpired(VKAccessToken expiredToken) {
					_cntx.dispatchStatusEventAsync(VKEvent.TOKEN_EXPIRED, "status");
				}

				@Override
				public void onAccessDenied(VKError authorizationError) {
					_cntx.dispatchStatusEventAsync(VKEvent.ACCESS_DENIED, "status");
				}
				@Override
				public void onReceiveNewToken(VKAccessToken newToken) {
					_cntx.dispatchStatusEventAsync(VKEvent.RECEIVE_NEW_TOKEN, "status");
				}
				
			};
			VKSdk.initialize(listener, 
				obj[0].getAsString(),
				VKAccessToken.tokenFromParameters(parameters)
			);
		} catch (IllegalStateException e) {
			makeToast("error");
		} catch (FRETypeMismatchException e) {
			makeToast("error");
		} catch (FREInvalidObjectException e) {
			makeToast("error");
		} catch (FREWrongThreadException e) {
			makeToast("error");
		} 
		
		return null;
	}
	private void makeToast(String meess){
			CharSequence texte = meess;
			int duratione = Toast.LENGTH_SHORT;
			Toast toaste = Toast.makeText(_cntx.getActivity().getApplicationContext(), texte, duratione);
			toaste.show();
	}

}
