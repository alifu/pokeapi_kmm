//
// Created by Alif Phincon on 26/09/25.
//

import ComposeApp
import SwiftUI

struct PokemonView: View {
    @StateObject var viewModel: PokemonViewModel
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        ZStack(alignment: .top) {
            Color(ColorType.companion.fromString(value: viewModel.pokemon?.types.first?.type.name ?? ""))
                .ignoresSafeArea()
            
            backgroundView(viewModel: viewModel)
            
            headerView
        }
        .navigationBarHidden(true)
        .navigationBarTitle("", displayMode: .inline)
    }
    
    var headerView: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .center, spacing: 0) {
                Button {
                    presentationMode.wrappedValue.dismiss()
                } label: {
                    Image("arrow_back")
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(Color(AppColor.white))
                        .frame(width: 32, height: 32)
                }
                
                Text((viewModel.pokemon?.name ?? "").capitalized)
                    .font(Font(kmmFont: FontUtils.shared.headerHeadline))
                    .foregroundColor(Color(AppColor.white))
                    .padding(EdgeInsets(top: 0, leading: 8, bottom: 0, trailing: 8))
                
                Spacer()
                
                VStack {
                    HStack(spacing: 0) {
                        Image("tag")
                            .resizable()
                            .renderingMode(.template)
                            .frame(width: 12, height: 12)
                            .foregroundColor(Color(AppColor.white))
                        Text(String(format: "%03d", viewModel.pokemon?.id ?? 0))
                            .font(Font(kmmFont: FontUtils.shared.caption))
                            .frame(height: 12)
                            .foregroundColor(Color(AppColor.white))
                    }
                }
            }
            .frame(height: 76)
            .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
            
            bannerView
        }
    }
    
    var bannerView: some View {
        HStack(alignment: .center, spacing: 0) {
            Spacer()
            
            Button {
                viewModel.prev()
            } label: {
                Image("chevron_left")
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(Color(AppColor.white))
            }
            .frame(width: 24, height: 24)
            .padding(.trailing, 40)
            
            if let img = viewModel.uiImage {
                Image(uiImage: img)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 200, height: 200)
            } else {
                ProgressView()
                    .frame(width: 200, height: 200)
            }
            
            Button {
                viewModel.next()
            } label: {
                Image("chevron_right")
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(Color(AppColor.white))
            }
            .frame(width: 24, height: 24)
            .padding(.leading, 40)
            
            Spacer()
        }
        .frame(height: 200)
        .padding(EdgeInsets(top: 8, leading: 20, bottom: 8, trailing: 20))
    }
}

fileprivate func backgroundView(viewModel: PokemonViewModel) -> some View {
    return VStack(alignment: .leading, spacing: 0) {
        HStack(alignment: .center, spacing: 0) {
            Spacer()
            Image("pokeball")
                .resizable()
                .renderingMode(.template)
                .foregroundColor(Color(AppColor.white).opacity(0.1))
                .frame(width: 208, height: 208, alignment: .trailing)
        }
        ZStack(alignment: .top) {
            RoundedRectangle(cornerRadius: 8)
                .fill(Color(AppColor.white))
            
            contentView(viewModel: viewModel)
                .padding(.top, 72)
        }
        .padding(8)
    }
}

fileprivate func contentView(viewModel: PokemonViewModel) -> some View {
    let columns: [GridItem] = [
        GridItem(.flexible(), spacing: 0, alignment: .bottom),
        GridItem(.fixed(2), spacing: 0),
        GridItem(.flexible(), spacing: 0, alignment: .bottom),
        GridItem(.fixed(2), spacing: 0),
        GridItem(.flexible(), alignment: .bottom)
    ]
    return ZStack(alignment: .top) {
        VStack {
            HStack(spacing: 8) {
                ForEach(viewModel.getTypes(), id: \.self) { type in
                    TypeCapsule(text: type.type.name.capitalized, backgroundColor: Color(ColorType.companion.fromString(value: type.type.name)))
                }
            }
            .padding(.bottom, 16)
            
            Text("About")
                .font(Font(kmmFont: FontUtils.shared.headerSubtitle1))
                .foregroundColor(Color(ColorType.companion.fromString(value: viewModel.pokemon?.types.first?.type.name ?? "")))
                .padding(.bottom, 16)
            
            HStack(alignment: .bottom, spacing: 0) {
                LazyVGrid(columns: columns) {
                    VStack(alignment: .center) {
                        HStack(spacing: 0) {
                            Image("weight")
                                .resizable()
                                .renderingMode(.template)
                                .foregroundColor(Color(AppColor.grayscaledark))
                                .frame(width: 16)
                                .padding(.trailing, 4)
                            Text(((viewModel.pokemon?.weight as? Double) ?? 0).toKg)
                                .font(Font(kmmFont: FontUtils.shared.body3))
                                .foregroundColor(Color(AppColor.grayscaledark))
                        }
                        .frame(height: 16)
                        .padding(EdgeInsets(top: 8, leading: 0, bottom: 8, trailing: 0))
                        
                        Text("Weight")
                            .font(Font(kmmFont: FontUtils.shared.caption))
                            .foregroundColor(Color(AppColor.grayscalemedium))
                    }
                    .frame(maxWidth: .infinity)
                    
                    Rectangle()
                        .frame(width: 2)
                        .foregroundColor(Color(AppColor.grayscalelight))
                    
                    VStack {
                        HStack(spacing: 0) {
                            Image("straighten")
                                .resizable()
                                .renderingMode(.template)
                                .foregroundColor(Color(AppColor.grayscaledark))
                                .frame(width: 16)
                                .padding(.trailing, 4)
                            Text(((viewModel.pokemon?.height as? Double) ?? 0).toMeters)
                                .font(Font(kmmFont: FontUtils.shared.body3))
                                .foregroundColor(Color(AppColor.grayscaledark))
                        }
                        .frame(height: 16)
                        .padding(EdgeInsets(top: 8, leading: 0, bottom: 8, trailing: 0))
                        
                        Text("Height")
                            .font(Font(kmmFont: FontUtils.shared.caption))
                            .foregroundColor(Color(AppColor.grayscalemedium))
                    }
                    .frame(maxWidth: .infinity)
                    
                    Rectangle()
                        .frame(width: 2)
                        .foregroundColor(Color(AppColor.grayscalelight))
                    
                    VStack(alignment: .center) {
                        VStack {
                            ForEach(viewModel.getAbilities(), id: \.self) { ability in
                                Text((ability.ability?.name ?? "").capitalized)
                                    .font(Font(kmmFont: FontUtils.shared.body3))
                                    .foregroundColor(Color(AppColor.grayscaledark))
                            }
                        }
                        .padding(.bottom, 8)
                        
                        Text("Moves")
                            .font(Font(kmmFont: FontUtils.shared.caption))
                            .foregroundColor(Color(AppColor.grayscalemedium))
                    }
                    .frame(maxWidth: .infinity)
                }
            }
            .padding(.bottom, 16)
            
            Text((viewModel.pokemonSpecies?.flavorTextEntries.first?.flavor_text ?? "").replacingOccurrences(of: "\n", with: " "))
                .font(Font(kmmFont: FontUtils.shared.body3))
                .foregroundColor(Color(AppColor.grayscaledark))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.bottom, 16)
                .padding(.leading, 8)
                .padding(.trailing, 8)
            
            Text("Base Stat")
                .font(Font(kmmFont: FontUtils.shared.headerSubtitle1))
                .foregroundColor(Color(ColorType.companion.fromString(value: viewModel.pokemon?.types.first?.type.name ?? "")))
                .padding(.bottom, 16)
            
            VStack(alignment: .leading, spacing: 0) {
                ForEach(viewModel.getStats(), id: \.stat.name) { stat in
                    PokemonStat(
                        title: stat.stat.displayName(),
                        stat: Int(stat.baseStat),
                        typeColor: Color(ColorType.companion.fromString(value: viewModel.pokemon?.types.first?.type.name ?? ""))
                    )
                }
            }
            .padding(.leading, 8)
            .padding(.trailing, 8)
        }
        .padding(8)
    }
}
