//
// Created by Alif Phincon on 26/09/25.
//

import Combine
import ComposeApp
import SwiftUI

class PokemonViewModel: ObservableObject {
    
    @Published var current: Pokedex? = nil
    @Published var pokemon: Pokemon?
    @Published var pokemonSpecies: PokemonSpecies?
    @Published var uiImage: UIImage? = nil
    
    private let sdk = PokemonSDK()
    private var cancellables = Set<AnyCancellable>()
    private let navigator: PokemonNavigatorCombine
    private let loader = SharedImageLoader(httpClient: KtorClientFactory().create())
    
    init(results: [Pokedex], startIndex: Int = 0) {
        navigator = PokemonNavigatorCombine(results: results, startIndex: startIndex)
        
        navigator.currentSubject
            .sink { [weak self] result in
                guard let `self` = self else { return }
                self.current = result
                self.uiImage = nil
                self.loadPokemonAndSpecies(name: self.current?.name ?? "")
            }
            .store(in: &cancellables)
    }
    
    func loadPokemonAndSpecies(name: String) {
        Task {
            do {
                // First: fetch Pokemon
                let p = try await sdk.fetchPokemon(nameOrId: name)
                self.pokemon = p
                //                print("Pokemon:", p)
                
                // Second: fetch Species (after Pokemon succeeds)
                let s = try await sdk.fetchSpecies(nameOrId: name)
                self.pokemonSpecies = s
                //                print("Species:", s)
                
                if let url = p.sprites?.other?.officialArtwork?.frontDefault {
                    let data = try await loader.load(url: url)
                    if let img = UIImage(data: data.toData()) {
                        await MainActor.run {
                            self.uiImage = img
                        }
                    }
                }
                
            } catch {
                print("Error fetching data:", error)
            }
        }
    }
    
    func next() {
        navigator.moveNext()
    }
    
    func prev() {
        navigator.movePrevious()
    }
    
    func getTypes() -> [TypeSlot] {
        return pokemon?.types ?? []
    }
    
    func getAbilities() -> [Ability] {
        return pokemon?.abilities ?? []
    }
    
    func getStats() -> [Stats] {
        return pokemon?.stats ?? []
    }
}
