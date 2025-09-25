//
// Created by Alif Phincon on 25/09/25.
//

import ComposeApp
import SwiftUI

struct SearchBox: View {
    @Binding var text: String
    var placeholder: String = "Search..."
    var onCommit: (() -> Void)? = nil

    var body: some View {
        HStack {
            Image("search")
                .renderingMode(.template)
                .resizable()
                .foregroundColor(Color(AppColor.primary))
                .frame(width: 16, height: 16)

            TextField(placeholder, text: $text, onCommit: {
                onCommit?()
            })
            .font(Font(kmmFont: FontUtils.shared.body3))
            .frame(height: 16)
            .textFieldStyle(PlainTextFieldStyle())
            .disableAutocorrection(true)
            .autocapitalization(.none)

            if !text.isEmpty {
                Button(action: {
                    text = ""
                }) {
                    Image("close")
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(Color(AppColor.primary))
                }
                .frame(width: 16, height: 16)
            }
        }
        .padding(EdgeInsets(top: 8, leading: 12, bottom: 8, trailing: 12))
        .background(
            RoundedRectangle(cornerRadius: 24, style: .continuous)
                .fill(Color(AppColor.white))
        )
        .overlay(
            Group {
                if text.isEmpty {
                    RoundedRectangle(cornerRadius: 24, style: .continuous)
                        .stroke(Color(AppColor.grayscalelight),
                                lineWidth: 1)
                        .shadow(color: Color(AppColor.grayscalemedium),
                                radius: 2, x: 2, y: 2)
                        .clipShape(
                            RoundedRectangle(cornerRadius: 24)
                        )
                } else {
                    RoundedRectangle(cornerRadius: 24, style: .continuous)
                        .stroke(Color(AppColor.grayscalelight), lineWidth: 0.5)
                        .shadow(color: Color(AppColor.grayscalemedium).opacity(1),
                                radius: 2, x: 0, y: 0)
                }
            }
        )
    }
}

struct SearchBox_Previews: PreviewProvider {
    static var previews: some View {

        VStack {
            SearchBox(text: .constant(""))
                .padding()
                .previewLayout(.sizeThatFits)

            SearchBox(text: .constant("Typing something..."))
                .padding()
                .previewLayout(.sizeThatFits)
        }
        .padding()
        .previewLayout(.sizeThatFits)
    }
}
