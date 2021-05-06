//
//  RecipeListViewModel.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-24.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    // Dependencies
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil
    
//    // Variables
//    var categories = [FoodCategory]()
//
//    // The current query
//    @Published var query = ""
//
//    // The Recipes
//    @Published var recipes: [Recipe] = []
//
//    // Selected category chip in SearchBar
//    @Published var selectedCategory: FoodCategory? = nil
//
//    // Show/hide progress bar
//    @Published var loading = false
//
//    // Page number for pagination
//    @Published var page = 1
//
//    // track the recipe at the bottom of the list so we know when to trigger pagination
//    private var bottomRecipe: Recipe? = nil
//
//    // Is a query currently in progress? This will prevent duplicate queries.
//    private var isQueryInProgress = false
//
//    let RECIPE_PAGINATION_PAGE_SIZE = 30
//
    
    // State
    @Published var state: RecipeListState = RecipeListState()
    
    init(
        searchRecipes: SearchRecipes,
        foodCategoryUtil: FoodCategoryUtil
    ){
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        onTriggerEvent(stateEvent: RecipeListEvents.NewSearch())
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent{
        case RecipeListEvents.NewSearch(): newSearch()
        case RecipeListEvents.NextPage(): nextPage()
        default:
            doNothing()
        }
    }
    
    func doNothing(){}
    
    private func resetSearchState(){
        let currentState = (self.state.copy() as! RecipeListState)
        var foodCategory = currentState.selectedCategory
        if(foodCategory?.value != currentState.query){
            foodCategory = nil
        }
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1, // reset
            query: currentState.query,
            recipes: [], // reset
            selectedCategory: foodCategory, // Maybe reset (see logic above)
            foodCategories: currentState.foodCategories,
            bottomRecipe:  currentState.bottomRecipe,
            isQueryInProgress: currentState.isQueryInProgress,
            queue: currentState.queue
        )
        
    }

    func onUpdateSelectedCategory(foodCategory: FoodCategory?){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            recipes: currentState.recipes,
            selectedCategory: foodCategory, // update selected FoodCategory
            foodCategories: currentState.foodCategories,
            bottomRecipe:  currentState.bottomRecipe,
            isQueryInProgress: currentState.isQueryInProgress,
            queue: currentState.queue
        )
        onUpdateQuery(query: foodCategory?.value ?? "")
        onTriggerEvent(stateEvent: RecipeListEvents.NewSearch())
    }

    func onUpdateQuery(query: String){
        updateState(query: query)
    }

    private func onUpdateBottomRecipe(recipe: Recipe){
        updateState(bottomRecipe: recipe)
    }

    private func incrementPage(){
        let currentState = (self.state.copy() as! RecipeListState)
        updateState(page: Int(currentState.page) + 1)
    }

    private func appendRecipes(recipes: [Recipe]){
        var currentState = (self.state.copy() as! RecipeListState)
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            recipes: currentRecipes, // update recipes
            selectedCategory: currentState.selectedCategory,
            foodCategories: currentState.foodCategories,
            bottomRecipe:  currentState.bottomRecipe,
            isQueryInProgress: currentState.isQueryInProgress,
            queue: currentState.queue
        )
        currentState = (self.state.copy() as! RecipeListState)
        self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
    }

    private func handleError(_ error: String){
       // TODO("Handle error - show dialog?")
        // 'appendToQueue'
    }

    private func newSearch() {
        resetSearchState()
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            try searchRecipes.execute(page: Int32(currentState.page), query: currentState.query).watch(block: {dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false

                    self.updateState(isLoading: loading)
                    
                    if(data != nil){
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                    if(message != nil){
                        self.handleError("\(message!)")
                    }
                }else{
                    self.handleError("ERROR: newSearch: DataState is nil")
                }
            })
        }catch{
            self.handleError("\(error)")
        }
    }
//
    func shouldQueryNextPage(recipe: Recipe) -> Bool {
        // check if looking at the bottom recipe
        // if lookingAtBottom -> proceed
        // if PAGE_SIZE * page <= recipes.length
        // if !queryInProgress
        // else -> do nothing
        let currentState = (self.state.copy() as! RecipeListState)
        if(recipe.id == currentState.bottomRecipe?.id){
            if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.recipes.count){
                if(!currentState.isQueryInProgress){
                    return true
                }
            }
        }
        return false
    }

    private func nextPage(){
        incrementPage()
        let currentState = (self.state.copy() as! RecipeListState)
        print("NEXT PAGE \(currentState.page)")
        if(currentState.page > 1){
            do{
                try searchRecipes.execute(page: Int32(currentState.page), query: currentState.query).watch(block: {dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false

                        self.updateState(isLoading: loading)
                        if(data != nil){
                            self.appendRecipes(recipes: data as! [Recipe])
                        }
                        if(message != nil){
                            self.handleError("ERROR: newSearch: \(message!)")
                        }
                    }else{
                        self.handleError("ERROR: newSearch: DataState is nil")
                    }
                })
            }catch{
                self.handleError("\(error)")
            }
        }
    }
    
    private func appendToQueue(message: GenericMessageInfo){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.queue
        queue.add(element: message)
        updateState(queue: queue)
    }
    
    /**
     *  Not everything can be conveniently updated with this function.
     *  Things like recipes, selectedCategory must have their own functions.
     */
    private func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        bottomRecipe: Recipe? = nil,
        isQueryInProgress: Bool? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            recipes: currentState.recipes ,
            selectedCategory: currentState.selectedCategory,
            foodCategories: currentState.foodCategories,
            bottomRecipe:  bottomRecipe ?? currentState.bottomRecipe,
            isQueryInProgress: isQueryInProgress ?? currentState.isQueryInProgress,
            queue: queue ?? currentState.queue
        )
    }
}



