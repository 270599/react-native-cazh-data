package id.cazh.data;
 
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.widget.Toast;

public class DataModule extends ReactContextBaseJavaModule {

  ReactApplicationContext context;
  
  public DataModule(ReactApplicationContext reactContext) {
    super(reactContext);

    this.context = reactContext;
  }
 
  @Override
  public String getName() {
    return "DataModule";
  }

  @ReactMethod
  public void show(String message) {
    Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_LONG).show();
  }

  @ReactMethod
  public void getString(String data, Promise promise) {
    String result = getStringResourceByName(data);
    WritableMap resultData = new WritableNativeMap();
    resultData.putString("data", result);
    promise.resolve(resultData);
  }

  private String getStringResourceByName(String aString) {
    try{
      String packageName = getCurrentActivity().getPackageName();
      PackageManager manager = getCurrentActivity().getPackageManager();
      Resources resources = manager.getResourcesForApplication(packageName);
      int resId = resources.getIdentifier(aString, "string", packageName);
      return resources.getString(resId);
    }catch (Exception e){
      return "";
    }
  }
}