//
// Created by Alif Phincon on 29/09/25.
//

import Foundation

extension Double {
    var toKg: String {
        return "\(forTrailingZero(self / 10)) kg"
    }

    var toMeters: String {
        return "\(forTrailingZero(self / 10)) m"
    }

    private func forTrailingZero(_ value: Double) -> String {
        return String(format: "%g", value)
    }
}