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
    
    // State
    @Published var state: RecipeDetailState =  RecipeDetailState()
    
    init(
        recipeId: Int,
        getRecipe: GetRecipe
    ) {
        self.getRecipe = getRecipe
        executeGetRecipe(recipeId: recipeId)
    }
    
    private func executeGetRecipe(recipeId: Int){
        do{
            try self.getRecipe.execute(recipeId: Int32(recipeId)).watch(block: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let error = dataState?.message
                    let loading = dataState?.isLoading ?? false
                    self.updateState(isLoading: loading)
                    
                    if(data != nil){
                        self.updateState(recipe: data! as Recipe)
                    }
                    if(error != nil){
                        self.handleError("\(error!)")
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
        // 'appendToQueue'
    }
    
    private func appendToQueue(message: GenericMessageInfo){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.queue
        queue.add(element: message)
        updateState(queue: queue)
    }
    
    private func updateState(
        isLoading: Bool? = nil,
        recipe: Recipe? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeDetailState)
        if isLoading != nil {
            self.state = self.state.doCopy(
                isLoading: isLoading!, // update isLoading
                recipe: currentState.recipe,
                queue: currentState.queue
            )
        }
        if recipe != nil {
            self.state = self.state.doCopy(
                isLoading: currentState.isLoading,
                recipe: recipe!, // Update recipe
                queue: currentState.queue
            )
        }
        if queue != nil {
            self.state = self.state.doCopy(
                isLoading: currentState.isLoading,
                recipe: currentState.recipe,
                queue: queue!  // Update queue
            )
        }
    }
}



