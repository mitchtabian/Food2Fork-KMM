//
//  RecipeDetailScreen.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-06-09.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeDetailScreen: View {
    
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
        Text("ID: \(recipeId)")
    }
}

//struct RecipeDetailScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeDetailScreen()
//    }
//}
