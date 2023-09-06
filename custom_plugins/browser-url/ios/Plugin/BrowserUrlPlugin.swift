import Foundation
import Capacitor
import WebKit

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(BrowserUrlPlugin)
public class BrowserUrlPlugin: CAPPlugin {
    
    var browser: Browser?

    @objc func open(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            if let viewController = UIApplication.shared.windows.first?.rootViewController {
                let title = call.getString("title") ?? ""
                let color = call.getString("color") ?? "#000000"
                let url = call.getString("url") ?? "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=77m5zx0gibqzog&redirect_uri=https://www.firabarcelona.com&state=foobar&scope=r_liteprofile"
                self.browser = Browser(url: url, presentingController: viewController, urlHandler: { [weak self] url, navigationAction in
                    if let resUrl = url {
                        if resUrl.contains("https://www.google.com/?code=") {
                            let code = self?.getCode(from: resUrl) ?? "empty"
                            print("CODE: ", code)
                            call.resolve(["code": code])
                        }
                    }
                })
                self.browser?.openModalWebview(url: url, title: title, color: color)
            }
        }
    }

    // @objc func close(_ call: CAPPluginCall) {
    //     browser?.close()
    //     browser = nil
    // }
    
    //@objc func removeAllListeners(_ call: CAPPluginCall) {
     //   self.bridge?.saveEventForLater(call)
    //}
    
    func getCode(from urlString: String) -> String? {
        guard let url = URL(string: urlString),
              let components = URLComponents(url: url, resolvingAgainstBaseURL: true),
              let queryItems = components.queryItems else {
            return nil
        }

        for item in queryItems {
            if item.name == "code" {
                return item.value
            }
        }

        return nil
    }
}
