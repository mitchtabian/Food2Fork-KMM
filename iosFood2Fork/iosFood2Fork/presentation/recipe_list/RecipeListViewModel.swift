//
//  RecipeListViewModel.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-06-02.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    // Dependencies
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil

    // State
    @Published var state: RecipeListState = RecipeListState()
    
    init(
            searchRecipes: SearchRecipes,
            foodCategoryUtil: FoodCategoryUtil
        ){
            self.searchRecipes = searchRecipes
            self.foodCategoryUtil = foodCategoryUtil
            // TODO("Perform a search")
        }
    
    func incrementPage(){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page + 1,
            query: currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentState.recipes,
            queue: currentState.queue
        )
    }
    
}
