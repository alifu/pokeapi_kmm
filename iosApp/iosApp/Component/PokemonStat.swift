//
// Created by Alif Phincon on 29/09/25.
//

import ComposeApp
import SwiftUI

struct PokemonStat: View {
    var title: String
    var stat: Int
    var typeColor: Color

    var body: some View {
        HStack(alignment: .center, spacing: 0) {
            Text(title)
                .font(Font(kmmFont: FontUtils.shared.headerSubtitle3))
                .foregroundColor(typeColor)
                .frame(width: 30, alignment: .trailing)

            Rectangle()
                .frame(width: 2, height: 16)
                .foregroundColor(Color(AppColor.grayscalelight))
                .padding(.leading, 4)

            Text("\(stat)")
                .font(Font(kmmFont: FontUtils.shared.body3))
                .foregroundColor(Color(AppColor.grayscaledark))
                .frame(width: 20, alignment: .trailing)
                .padding(.leading, 4)

            GeometryReader { geometry in
                VStack {
                    ZStack(alignment: .leading) {
                        Rectangle()
                            .frame(height: 4)
                            .foregroundColor(typeColor.opacity(0.2))
                            .padding(.leading, 4)

                        Rectangle()
                            .frame(width: geometry.size.width * (CGFloat(stat)/100), height: 4)
                            .foregroundColor(typeColor)
                            .padding(.leading, 4)
                    }
                    .frame(height: 4)
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
            }
            .frame(height: 16)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

struct PokemonStat_Previews: PreviewProvider {
    static var previews: some View {
        PokemonStat(title: "HP", stat: 50, typeColor: Color(AppColor.wireframe))
    }
}
