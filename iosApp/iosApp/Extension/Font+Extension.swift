//
// Created by Alif Phincon on 25/09/25.
//

import ComposeApp
import SwiftUI

extension Font {
    init(kmmFont: ComposeApp.FontBase) {
        self = Font(kmmFont.uiFont)
    }
}
