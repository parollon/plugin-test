//
//  Browser.swift
//  Plugin
//
//  Created by Miquel Oliveros on 2/5/23.
//  Copyright © 2023 Max Lynch. All rights reserved.
//

import Foundation
import WebKit
import Capacitor

class Browser: NSObject, WKNavigationDelegate {
    
    var webView: WKWebView?
    var urlHandler: ((String?, WKNavigationAction) -> Void)?
    var presentingController: UIViewController
    var modalController: UIViewController?
    
    init(url: String, presentingController: UIViewController, urlHandler: @escaping (String?, WKNavigationAction) -> Void) {
        self.presentingController = presentingController
        super.init()
        self.urlHandler = urlHandler
    }
    
    func openModalWebview(url: String, title: String, color: String) {
        let webConfiguration = WKWebViewConfiguration()
        webView = WKWebView(frame: .zero, configuration: webConfiguration)
        if let webView = webView {
            let myURL = URL(string: url)
            let myRequest = URLRequest(url: myURL!)
            webView.load(myRequest)
            webView.navigationDelegate = self

            // Create a new UIViewController to present modally
            let controller = UIViewController()
            controller.view.addSubview(webView)
            webView.frame = controller.view.bounds

            // Present the modal UIViewController
            presentingController.present(controller, animated: true, completion: nil)
            self.modalController = controller
        }
    }

    @objc func closeButtonPressed() {
        close()
    }
    
    func close() {
        modalController?.dismiss(animated: true, completion: nil)
        webView?.stopLoading()
        webView = nil
    }
    
    public func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction, decisionHandler: @escaping (WKNavigationActionPolicy) -> Void) {
        let url = navigationAction.request.url?.absoluteString
        if let resUrl = url {
            if resUrl.contains("https://www.firabarcelona.com/?code=") {
                close()
            }
        }
        urlHandler?(url, navigationAction)
        decisionHandler(.allow)
    }
}
