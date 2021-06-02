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
            onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
        }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            doNothing()
        case is RecipeListEvents.NextPage:
            doNothing()
        case is RecipeListEvents.OnUpdateQuery:
            doNothing()
        case is RecipeListEvents.OnSelectCategory:
            doNothing()
        case RecipeListEvents.OnRemoveHeadMessageFromQueue():
            doNothing()
        default:
            doNothing()
        }
    }
    
    private func loadRecipes(){
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            try searchRecipes.execute(
                page: Int32(currentState.page),
                query: currentState.query
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false

                    self.updateState(isLoading: loading)

                    if(data != nil){
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                    if(message != nil){
                        self.handleMessageByUIComponentType(message!.build())
                    }
                }
            })
        }catch{
            // build an error
            //self.handleMessageByUIComponentType(message!.build())
        }
    }
    
    private func appendRecipes(recipes: [Recipe]){
        for recipe in recipes {
            print("\(recipe.title)")
        }
        // TODO("append recipes to state")
    }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        // TODO("append to queue or 'None'")
    }
    
    private func doNothing(){
        // does nothing
    }
    /**
     *  Not everything can be conveniently updated with this function.
     *  Things like recipes and selectedCategory must have their own functions.
     *  Basically if more then one action must be taken then it cannot be updated with this function.
     *  ex: updating selected category requires us to 1) update category, 2) update the query, 3) trigger new search event
     */
        func updateState(
            isLoading: Bool? = nil,
            page: Int? = nil,
            query: String? = nil,
            queue: Queue<GenericMessageInfo>? = nil
        ){
            let currentState = (self.state.copy() as! RecipeListState)
            self.state = self.state.doCopy(
                isLoading: isLoading ?? currentState.isLoading,
                page: Int32(page ?? Int(currentState.page)),
                query: query ?? currentState.query,
                selectedCategory: currentState.selectedCategory,
                recipes: currentState.recipes ,
                queue: queue ?? currentState.queue
            )
        }
    
}
