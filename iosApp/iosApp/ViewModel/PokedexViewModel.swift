//
// Created by Alif on 24/09/25.
//

import Foundation
import ComposeApp

class PokedexViewModel: ObservableObject {
    private let sdk = PokemonSDK()

    @Published var pokedex: PokedexResponse?
    @Published var error: String?
    @Published var searchText: String = ""
    @Published var showSortMenu: Bool = false
    @Published var pokemons: [Pokedex] = []

    init() {
        // defer network work to a Task
        Task {
            await loadPokedex()
        }
    }

    func loadPokedex() async {
        do {
            let res = try await sdk.fetchPokedex(limit: 20, offset: 0)
            pokedex = res
            pokemons.append(contentsOf: res.results)
        } catch {
            print("Kotlin/Native error:", error)
        }
    }
}
