//
// Created by Alif Phincon on 29/09/25.
//

import ComposeApp
import SwiftUI

struct TypeCapsule: View {
    let text: String
    var systemImage: String? = nil
    var font: Font = Font(kmmFont: FontUtils.shared.headerSubtitle3)
    var textColor: Color = Color(AppColor.white)
    var backgroundColor: Color = Color(AppColor.grayscalemedium)
    var paddingV: CGFloat = 6
    var paddingH: CGFloat = 12
    var cornerRadius: CGFloat = 16
    var borderColor: Color? = nil
    var borderWidth: CGFloat = 1
    var shadow: Bool = false
    var action: (() -> Void)? = nil

    var body: some View {
        let label = HStack(spacing: 8) {
            if let systemImage = systemImage {
                Image(systemName: systemImage)
                    .font(font)
                    .accessibility(hidden: true)
            }
            Text(text)
                .font(font)
                .lineLimit(1)
        }
        .foregroundColor(textColor)
        .padding(.vertical, paddingV)
        .padding(.horizontal, paddingH)
        .background(
            RoundedRectangle(cornerRadius: cornerRadius, style: .continuous)
                .fill(backgroundColor)
        )
        .overlay(
            Group {
                if let stroke = borderColor {
                    RoundedRectangle(cornerRadius: cornerRadius, style: .continuous)
                        .stroke(stroke, lineWidth: borderWidth)
                }
            }
        )
        .fixedSize()
        .modifier(ShadowIfNeeded(enabled: shadow))

        if let action = action {
            Button(action: action) {
                label
            }
            .buttonStyle(PlainButtonStyle())
        } else {
            label
        }
    }
}

// MARK: - Preview and examples
struct TypeCapsule_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing: 12) {
            TypeCapsule(text: "Default")
            TypeCapsule(text: "With icon", systemImage: "star.fill", textColor: .white, backgroundColor: Color(AppColor.primary))
            TypeCapsule(text: "Tag", textColor: .white, backgroundColor: .green, cornerRadius: 10)
            TypeCapsule(text: "Outlined", textColor: Color(AppColor.primary), backgroundColor: .clear, borderColor: Color(AppColor.primary))
            TypeCapsuleSelectableExample()
        }
        .padding()
        .previewLayout(.sizeThatFits)
    }

    struct TypeCapsuleSelectableExample: View {
        @State private var selected = false
        var body: some View {
            TypeCapsule(
                text: selected ? "Selected" : "Tap me",
                systemImage: selected ? "checkmark" : nil,
                font: .callout.bold(),
                textColor: selected ? .white : .primary,
                backgroundColor: selected ? .accentColor : Color(.systemGray5),
                cornerRadius: 12,
                shadow: true
            ) {
                withAnimation(.easeInOut) { selected.toggle() }
            }
        }
    }
}
