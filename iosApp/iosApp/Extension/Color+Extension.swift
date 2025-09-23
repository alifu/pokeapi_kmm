//
// Created by Alif on 23/09/25.
//

import SwiftUI
import ComposeApp

extension Color {
    init(_ type: ColorType) {
        self.init(uiColor: type.toUIColor())
    }
    init(_ app: AppColor) {
        self.init(uiColor: app.toUIColor())
    }
}
