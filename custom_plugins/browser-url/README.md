# url-browser

Plugin to open webview and get back url.

## Install

```bash
npm install url-browser
npx cap sync
```

## API

<docgen-index>

* [`open(...)`](#open)
* [`addListener('browserFinished', ...)`](#addlistenerbrowserfinished)
* [`addListener('urlCurrent', ...)`](#addlistenerurlcurrent)
* [`removeAllListeners()`](#removealllisteners)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### open(...)

```typescript
open(options: ViewOptions) => Promise<void>
```

| Param         | Type                                                |
| ------------- | --------------------------------------------------- |
| **`options`** | <code><a href="#viewoptions">ViewOptions</a></code> |

--------------------


### addListener('browserFinished', ...)

```typescript
addListener(eventName: 'browserFinished', listenerFunc: () => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                           |
| ------------------ | ------------------------------ |
| **`eventName`**    | <code>'browserFinished'</code> |
| **`listenerFunc`** | <code>() =&gt; void</code>     |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### addListener('urlCurrent', ...)

```typescript
addListener(eventName: 'urlCurrent', listenerFunc: (url: OpenOptions) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                  |
| ------------------ | --------------------------------------------------------------------- |
| **`eventName`**    | <code>'urlCurrent'</code>                                             |
| **`listenerFunc`** | <code>(url: <a href="#openoptions">OpenOptions</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

--------------------


### Interfaces


#### ViewOptions

| Prop        | Type                |
| ----------- | ------------------- |
| **`title`** | <code>string</code> |
| **`color`** | <code>string</code> |
| **`url`**   | <code>string</code> |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### OpenOptions

| Prop      | Type                |
| --------- | ------------------- |
| **`url`** | <code>string</code> |

</docgen-api>
