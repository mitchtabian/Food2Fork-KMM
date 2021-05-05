//
//  RecipeDetailViewModel.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-05-05.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeDetailViewModel: ObservableObject {
    
    // Dependencies
    let getRecipe: GetRecipe
    
    // Variables
    
    // The Recipe that is being displayed in UI
    @Published var recipe: Recipe? = nil
    
    // Show/hide progress bar
    @Published var loading = false
    
    init(
        recipeId: Int,
        getRecipe: GetRecipe
    ) {
        self.getRecipe = getRecipe
        executeGetRecipe(recipeId: recipeId)
    }
    
    private func executeGetRecipe(recipeId: Int){
        do{
            try self.getRecipe.execute(recipeId: Int32(recipeId)).watch(block: {dataState in
                if dataState != nil {
                    let _data = dataState?.data
                    let _error = dataState?.message
                    let _loading = dataState?.isLoading ?? false
                    
                    self.loading = _loading
                    
                    if(_data != nil){
                        self.recipe = _data! as Recipe
                    }
                    if(_error != nil){
                        self.handleError("\(_error!)")
                    }
                }else{
                    self.handleError("ERROR: getRecipe: DataState is nil")
                }
            })
        }catch{
            self.handleError("\(error)")
        }
    }
    
    private func handleError(_ error: String){
       // TODO("Handle error - show dialog?")
    }
}



