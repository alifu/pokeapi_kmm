//
// Created by Alif Phincon on 29/09/25.
//

import ComposeApp
import SwiftUI

struct ShadowIfNeeded: ViewModifier {
    var enabled: Bool
    func body(content: Content) -> some View {
        if enabled {
            content.shadow(color: Color(AppColor.primary).opacity(0.12), radius: 4, x: 0, y: 2)
        } else {
            content
        }
    }
}