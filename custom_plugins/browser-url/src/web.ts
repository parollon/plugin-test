import { WebPlugin } from '@capacitor/core';

import type { BrowserUrlPlugin, ViewOptions } from './definitions';

export class BrowserUrlWeb extends WebPlugin implements BrowserUrlPlugin {
  
  _lastWindow: Window | null;

  constructor() {
    super();
    this._lastWindow = null;
  }

  async open(options: ViewOptions): Promise<void> {
    console.log(options);
    this._lastWindow = null;
  }
}
