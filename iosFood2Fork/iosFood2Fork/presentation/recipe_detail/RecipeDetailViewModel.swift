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
        onTriggerEvent(stateEvent: RecipeDetailEvents.GetRecipe(recipeId: Int32(recipeId)))
    }
    
    func onTriggerEvent(stateEvent: RecipeDetailEvents){
            switch stateEvent {
                case is RecipeDetailEvents.GetRecipe:
                    getRecipe(recipeId: Int((stateEvent as! RecipeDetailEvents.GetRecipe).recipeId))
                case is RecipeDetailEvents.OnRemoveHeadMessageFromQueue:
                    doNothing()
                default: doNothing()
            }
        }
        
        private func getRecipe(recipeId: Int){
            do{
                try self.getRecipe.execute(
                    recipeId: Int32(recipeId)
                ).collectCommon(
                    coroutineScope: nil,
                    callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        self.updateState(isLoading: loading)
                        
                        if(data != nil){
                            self.updateState(recipe: data! as Recipe)
                        }
                        if(message != nil){
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }else{
                        print("GetRecipe: DataState is nil")
                    }
                })
            }catch{
                print("GetRecipe: ERROR: \(error)")
            }
        }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        // TODO("handle error messages")
    }
        
    private func doNothing(){}
    
    private func updateState(
        isLoading: Bool? = nil,
        recipe: Recipe? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeDetailState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            recipe: recipe ?? currentState.recipe,
            queue: queue ?? currentState.queue
        )
    }
    
}
