import { Component } from '@angular/core';
import { BrowserUrl, ViewOptions } from 'custom_plugins/browser-url';
@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor() {}

  runPlugin(){
    let option: ViewOptions = {
      title: 'PluginTest Webview',
      color: '#F08014',
      url: "https://capacitorjs.com/",
    };
    BrowserUrl.open(option);
  }

}
