import type { PluginListenerHandle } from '@capacitor/core';

export interface BrowserUrlPlugin {
  open(options: ViewOptions): Promise<void>;
  addListener(
    eventName: 'browserFinished',
    listenerFunc: () => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(
    eventName: 'urlCurrent',
    listenerFunc: (url: OpenOptions) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
  removeAllListeners(): Promise<void>;
}

export interface ViewOptions {
  title: string;
  color: string;
  url: string;
}

export interface OpenOptions {
  url: string;
}