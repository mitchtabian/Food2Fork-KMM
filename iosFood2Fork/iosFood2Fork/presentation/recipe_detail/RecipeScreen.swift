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
    
    private let appModule: AppModule
    private let cacheModule: CacheModule
    private let getRecipeModule: GetRecipeModule
    private let recipeId: Int
    
    @ObservedObject var viewModel: RecipeDetailViewModel
    
    init(
        recipeId: Int,
        appModule: AppModule,
        cacheModule: CacheModule
         ) {
        self.recipeId = recipeId
        self.appModule = appModule
        self.cacheModule = cacheModule
        self.getRecipeModule = GetRecipeModule(
            appModule: self.appModule,
            cacheModule: self.cacheModule
        )
        viewModel = RecipeDetailViewModel(
            recipeId: self.recipeId,
            getRecipe: self.getRecipeModule.getRecipe
        )
    }
    
    var body: some View {
        if viewModel.recipe != nil {
            RecipeView(
                recipe: viewModel.recipe!,
                dateUtil: appModule.dateUtil
            )
        }
        else{
            Text("Error")
        }
    }
}

struct RecipeScreen_Previews: PreviewProvider {
    
    static var previews: some View {
        RecipeScreen(
            recipeId: 1,
            appModule: AppModule(),
            cacheModule: CacheModule()
        )
    }
}
