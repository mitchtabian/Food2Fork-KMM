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
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            doNothing()
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
