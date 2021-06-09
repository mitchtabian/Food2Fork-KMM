//
//  RecipeDetailViewModel.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-06-09.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeDetailViewModel: ObservableObject {
    
    // Dependencies
    private let getRecipe: GetRecipe
    
    // State
    @Published var state: RecipeDetailState =  RecipeDetailState()
    
    init(
        recipeId: Int,
        getRecipe: GetRecipe
    ) {
        self.getRecipe = getRecipe
        // TODO("Get the recipe from cache")
    }
    
}
