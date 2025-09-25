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
            print(pokedex)
        } catch {
            // error = "Load failed: \(error)"
            print("Kotlin/Native error:", error)
        }
    }

    // func loadPokedex(limit: Int32 = 50, offset: Int32 = 0) {
    //
    //     Task {
    //         do {
    //
    //             let res = try await sdk.fetchPokedex(limit: limit, offset: offset)
    //             // DispatchQueue.main.async { self.pokedex = res }
    //         } catch {
    //             // handle
    //         }
    //     }
    // }

    // func loadPokemon(name: String) {
    //     Task {
    //         do {
    //             let p = try await sdk.fetchPokemon(nameOrId: name)
    //             DispatchQueue.main.async { self.pokemon = p }
    //         } catch {
    //             // handle
    //         }
    //     }
    // }
    //
    // func loadSpecies(name: String) {
    //     Task {
    //         do {
    //             let s = try await sdk.fetchSpecies(nameOrId: name)
    //             DispatchQueue.main.async { self.species = s }
    //         } catch {
    //             // handle
    //         }
    //     }
    // }
}
