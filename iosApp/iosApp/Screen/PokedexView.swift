//
// Created by Alif on 23/09/25.
//

import ComposeApp
import SwiftUI

struct PokedexView: View {
    @StateObject var viewModel = PokedexViewModel()
    @State private var buttonFrame: CGRect = .zero
    @State private var selectedPokemon: Int?
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
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
                
                NavigationLink(
                    destination: Group {
                        if let index = selectedPokemon {
                            PokemonView(
                                viewModel: PokemonViewModel(results: viewModel.pokemons, startIndex: index)
                            )
                        }
                    },
                    isActive: Binding(
                        get: { selectedPokemon != nil },
                        set: { if !$0 { selectedPokemon = nil } }
                    )
                ) {
                    EmptyView()
                }
                .hidden()
                
                ZStack {
                    RoundedRectangle(cornerRadius: 8)
                        .fill(Color(AppColor.white))
                    
                    ScrollView {
                        LazyVGrid(columns: columns, spacing: 8) {
                            ForEach(Array(viewModel.pokemons.enumerated()), id: \.offset) { (index, element) in

                                Button {
                                    selectedPokemon = index
                                } label: {
                                    let item = element
                                    PokemonCard(
                                        name: item.name,
                                        imageURL: item.imageURL,
                                        idTag: item.id ?? ""
                                    )
                                }
                            }
                        }
                        .padding()
                    }
                }
                .padding(8)
            }
        }
    }
}
