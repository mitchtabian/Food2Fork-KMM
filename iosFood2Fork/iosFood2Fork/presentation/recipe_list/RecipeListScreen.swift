//
//  RecipeListScreen.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-06-02.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    private let foodCategories: [FoodCategory]
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategoryUtil: foodCategoryUtil
        )
        self.foodCategories = foodCategoryUtil.getAllFoodCategories()
    }
    
    var body: some View {
        NavigationView{
            VStack{
                SearchAppBar(
                    query: viewModel.state.query,
                    selectedCategory: viewModel.state.selectedCategory,
                    foodCategories: foodCategories,
                    onTriggerEvent: { event in
                        viewModel.onTriggerEvent(stateEvent: event)
                    }
                )
                List {
                    ForEach(viewModel.state.recipes, id: \.self.id){ recipe in
                        ZStack{
                            VStack{
                                RecipeCard(recipe: recipe)
                                    .onAppear(perform: {
                                        if viewModel.shouldQueryNextPage(recipe: recipe){
                                            viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                                        }
                                    })
                            }
                            NavigationLink(
                                destination: Text("\(recipe.title)")
                            ){
                                // workaround for hiding arrows
                                EmptyView()
                            }.hidden().frame(width: 0)
                        }
                        .listRowInsets(EdgeInsets())
                        .padding(.top, 10)
                    }
                }
                .listStyle(PlainListStyle())
                .background(Color.gray)
            }
            .navigationBarHidden(true)
        }
    }
}

// Previews are annoying
//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
