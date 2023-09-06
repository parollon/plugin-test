# Browser-url pluguin!
## Migracio del plugin

```bash
$ npx  @capacitor/plugin-migration-v4-to-v5@latest
[info] Updating Android files
[info] Remove android.enableJetifier=true from gradle.properties
[info] Updating build.gradle
[info] Set com.android.tools.build:gradle = 8.0.0.
[info] Set compileSdkVersion = 33.
[info] Set targetSdkVersion = 33.
[info] Set androidxAppCompatVersion = 1.6.1.
[info] Set junitVersion = 4.13.2.
[info] Set androidxJunitVersion = 1.1.5.
[info] Set androidxEspressoCoreVersion = 3.5.1.
[info] Moving package from AndroidManifest.xml to build.gradle
[info] Updating gradle wrapper file
[info] Updating gradle files
[info] Plugin migrated to Capacitor 5!
```

### Problemes trobats

 1.  No detecta els estils quan a capacitor 4 sí
```
/Users/use-itsl/Documents/IonicProjects/PluginTest/custom_plugins/browser-url/android/src/main/java/com/useit/browser/BrowserUrl.java:46: error: cannot find symbol
    setTheme(R.style.AppTheme_NoActionBar);
              ^
  symbol:   variable style
  location: class R
-------------------------------
Duplicate key: (row=style, column=AppTheme_NoActionBar), values: [UNDEFINED style AppTheme_NoActionBar = 0x7f0e0008, UNDEFINED style AppTheme_NoActionBar = 0x7f0e0006].

```
ho he solucionat creant els propis estils
```java
@Override  
protected void onCreate(Bundle savedInstanceState) {  
  super.onCreate(savedInstanceState);  
    ...
    setTheme(R.style.AppTheme_NoActionBar2);
    ...
}
```
styles.xml
```xml
<resources>   
  <style name="AppTheme_NoActionBar2">  
    <item name="windowActionBar">false</item>  
    <item name="windowNoTitle">true</item>  
    <item name="android:background">@null</item>  
  </style>  
</resources>
```
 2.  Problema amb META-INF

```
2 files found with path 'META-INF/androidx.localbroadcastmanager_localbroadcastmanager.version'.
Adding a packagingOptions block may help, please refer to
https://developer.android.com/reference/tools/gradle-api/8.0/com/android/build/api/dsl/ResourcesPackagingOptions
for more information
```
Ho he solucionat posant aixo al gradle a nivell de projecte `build.gradle(:app)`
```java
android {
   ...
    packagingOptions {
        exclude 'META-INF/androidx.localbroadcastmanager_localbroadcastmanager.version'
    }
}
```

## Construccio del plugin

```bash
cd custom_plugins/browser-url
npm install
npm run build
```

## Instal·lació del plugin
```bash
npm install ./custom_plugins/browser-url
```
### Important
cal afegir la Activity al Manifest


```xml
<application>
	<activity  android:name="com.useit.browser.BrowserUrl"  />
</application>
```
