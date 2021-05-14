//
//  RecipeScreen.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeScreen: View {
    
    private let cacheModule: CacheModule
    private let getRecipeModule: GetRecipeModule
    private let recipeId: Int
    private let datetimeUtil = DatetimeUtil()
    
    @ObservedObject var viewModel: RecipeDetailViewModel
    
    init(
        recipeId: Int,
        cacheModule: CacheModule
         ) {
        self.recipeId = recipeId
        self.cacheModule = cacheModule
        self.getRecipeModule = GetRecipeModule(
            cacheModule: self.cacheModule
        )
        viewModel = RecipeDetailViewModel(
            recipeId: self.recipeId,
            getRecipe: self.getRecipeModule.getRecipe
        )
    }
    
    var body: some View {
            if viewModel.state.recipe != nil {
                RecipeView(
                    recipe: viewModel.state.recipe!,
                    dateUtil: datetimeUtil
                )
            }
            else{
                NavigationView { // NavigationView is needed for alert to work?
                    Text("Error")
                        .alert(isPresented: $viewModel.showDialog, content: {
                            let first = viewModel.state.queue.peek()!
                            return GenericMessageInfoAlert().build(
                                message: first,
                                onRemoveHeadMessage: viewModel.removeHeadFromQueue
                            )
                        })
                }
            }
            if viewModel.state.isLoading { // this is actually pointless b/c SwiftUI preloads this view
                VStack{
                    Spacer()
                    ProgressView("Loading Recipe Details...")
                    Spacer()
                }
            }
    }
}

struct RecipeScreen_Previews: PreviewProvider {
    
    static var previews: some View {
        RecipeScreen(
            recipeId: 1,
            cacheModule: CacheModule()
        )
    }
}
