package ru.fysx.vksdkane.projectedMethods;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.vk.sdk.VKSdk;

public class GetAccessToken implements FREFunction {

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		FREObject accessToken = null;
		try {
			accessToken = FREObject.newObject("com.vk.sdk.VKAccessToken", new FREObject[]{
					FREObject.newObject(VKSdk.getAccessToken().accessToken),
					FREObject.newObject(VKSdk.getAccessToken().expiresIn),
					FREObject.newObject(VKSdk.getAccessToken().userId),
					FREObject.newObject(VKSdk.getAccessToken().secret),
					FREObject.newObject(VKSdk.getAccessToken().httpsRequired)
			});
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREASErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRENoSuchNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessToken;
	}

}
