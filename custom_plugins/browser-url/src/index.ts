import { registerPlugin } from '@capacitor/core';

import type { BrowserUrlPlugin } from './definitions';

const BrowserUrl = registerPlugin<BrowserUrlPlugin>('BrowserUrl', {
  web: () => import('./web').then(m => new m.BrowserUrlWeb()),
});

export * from './definitions';
export { BrowserUrl };
