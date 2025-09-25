//
// Created by Alif on 23/09/25.
//

import ComposeApp
import SwiftUI

struct PokedexView: View {
    @StateObject var viewModel = PokedexViewModel()
    @State private var buttonFrame: CGRect = .zero
    
    var body: some View {
        ZStack(alignment: .top) {
            Color(AppColor.primary)
                .ignoresSafeArea()
            VStack(alignment: .leading) {
                HStack(spacing: 0) {
                    Image("pokeball")
                        .resizable()
                        .renderingMode(.template)
                        .frame(width: 24, height: 24)
                        .padding(.trailing, 8)
                        .foregroundColor(Color(AppColor.white))
                    Text("Pokedex")
                        .font(Font(kmmFont: FontUtils.shared.headerHeadline))
                        .foregroundColor(Color(AppColor.white))
                    Spacer()
                }
                .padding(.leading, 16)
                
                HStack(spacing: 0) {
                    SearchBox(text: $viewModel.searchText)
                        .padding(.leading, 16)
                        .padding(.trailing, 16)
                    
                    SortButton(isSorted: .constant(false), buttonFrame: $buttonFrame) {
                        viewModel.showSortMenu.toggle()
                    }
                    .padding(.trailing, 16)
                }
            }
        }
    }
}
