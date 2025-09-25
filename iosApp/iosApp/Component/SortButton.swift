//
// Created by Alif Phincon on 25/09/25.
//

import ComposeApp
import SwiftUI

struct SortButton: View {
    
    @Binding var isSorted: Bool
    @Binding var buttonFrame: CGRect
    var action: (() -> Void)? = nil
    
    var body: some View {
        let label = ZStack {
            Image("sort")
                .renderingMode(.template)
                .resizable()
                .frame(width: 16, height: 16)
                .foregroundColor(Color(AppColor.primary))
        }
            .padding(8)
            .background(
                RoundedRectangle(cornerRadius: 24, style: .continuous)
                    .fill(Color(AppColor.white))
            )
            .overlay(
                Group {
                    if isSorted {
                        Circle()
                            .stroke(Color(AppColor.grayscalelight), lineWidth: 0.5)
                            .shadow(color: (Color(AppColor.grayscalemedium)).opacity(1),
                                    radius: 2, x: 0, y: 0)
                    } else {
                        Circle()
                            .stroke(Color(AppColor.grayscalelight),
                                    lineWidth: 1)
                            .shadow(color: Color(AppColor.grayscalemedium),
                                    radius: 2, x: 2, y: 2)
                            .clipShape(
                                Circle()
                            )
                    }
                }
            )
            .background(
                GeometryReader { geo in
                    Color.clear
                        .onAppear {
                            buttonFrame = geo.frame(in: .global)
                        }
                        .onChange(of: geo.frame(in: .global)) { newValue in
                            buttonFrame = newValue
                        }
                }
            )
        
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

struct SortButtonComponent_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing: 12) {
            SortButton(isSorted: .constant(false), buttonFrame: .constant(.zero))
            SortButton(isSorted: .constant(true), buttonFrame: .constant(.zero))
        }
    }
}
