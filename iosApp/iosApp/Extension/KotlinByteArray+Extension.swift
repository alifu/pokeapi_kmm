//
// Created by Alif Phincon on 26/09/25.
//

import ComposeApp
import SwiftUI

extension KotlinByteArray {
    func toData() -> Data {
        let buffer: [UInt8] = (0..<self.size).map { index in
            UInt8(bitPattern: self.get(index: index))
        }
        return Data(buffer)
    }
}
