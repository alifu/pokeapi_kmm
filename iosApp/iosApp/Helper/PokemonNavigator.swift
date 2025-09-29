//
// Created by Alif Phincon on 29/09/25.
//

import Combine
import ComposeApp
import Foundation

final class PokemonNavigatorCombine: ObservableObject {

   private let navigator: PokemonNavigator
   let currentSubject: CurrentValueSubject<Pokedex?, Never>

   init(results: [Pokedex], startIndex: Int = 0) {
       self.navigator = PokemonNavigator(allResults: results, startIndex: Int32(startIndex))
       self.currentSubject = CurrentValueSubject(self.navigator.current)
   }

   func moveNext() {
       let new = navigator.moveNext()
       currentSubject.send(new)
   }

   func movePrevious() {
       let new = navigator.movePrevious()
       currentSubject.send(new)
   }
}
