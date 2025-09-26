
//
// Created by Alif Phincon on 26/09/25.
//

import ComposeApp
import SwiftUI

struct PokemonCard: View {
    let name: String
    let imageURL: String?
    let idTag: String
    
    @State private var uiImage: UIImage? = nil
    
    private let loader = SharedImageLoader(httpClient: KtorClientFactory().create())
    
    var body: some View {
        ZStack(alignment: .center) {
            VStack(spacing: 0) {
                Spacer()
                ZStack(alignment: .bottom) {
                    Rectangle()
                        .fill(Color(AppColor.background))
                        .frame(height: 44, alignment: .bottom)
//                        .cornerRadius(7, corners: [.topLeft, .topRight])
                    
                    Text(name)
                        .frame(alignment: .center)
                        .font(Font(kmmFont: FontUtils.shared.body3))
                        .foregroundColor(Color(AppColor.grayscaledark))
                        .padding(EdgeInsets(top: 0, leading: 8, bottom: 4, trailing: 8))
                }
            }
            
            if let img = uiImage {
                Image(uiImage: img)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 60, height: 60)
            } else {
                ProgressView()
                    .frame(width: 60, height: 60)
                    .onAppear {
                        loadImage()
                    }
            }
            
            VStack {
                HStack {
                    Spacer()
                    HStack(spacing: 0) {
                        Image("tag")
                            .resizable()
                            .renderingMode(.template)
                            .frame(width: 12, height: 12)
                            .foregroundColor(Color(AppColor.grayscalemedium))
                        Text(String(format: "%03d", Int(idTag) ?? 0))
                            .font(Font(kmmFont: FontUtils.shared.caption))
                            .frame(height: 12)
                            .foregroundColor(Color(AppColor.grayscalemedium))
                    }
                    .padding(EdgeInsets(top: 6, leading: 0, bottom: 0, trailing: 10))
                }
                Spacer()
            }
        }
        .frame(height: 108)
        .padding(0)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.white)
        )
        .clipShape(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
        )
        .shadow(color: Color(AppColor.grayscalemedium),
                radius: 2, x: 0, y: 1)
    }
    
    private func loadImage() {
        guard let url = imageURL else { return }
        loader.load(url: url) { data, error in
            if let byteArray = data {
                let nsData = byteArray.toData()
                if let img = UIImage(data: nsData) {
                    DispatchQueue.main.async {
                        self.uiImage = img
                    }
                }
            }
        }
    }
}
