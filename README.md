# Browser-url plugin test!

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

