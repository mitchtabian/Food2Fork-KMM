//
//  RecipeListScreen.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 14.0, *)
struct RecipeListScreen: View {
    
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
        // dismiss keyboard when drag starts
        UIScrollView.appearance().keyboardDismissMode = .onDrag
    }
    
    var body: some View {
        NavigationView{
            ZStack{
                VStack{
                    SearchAppBar(
                        query: viewModel.state.query,
                        selectedCategory: viewModel.state.selectedCategory,
                        foodCategories: foodCategories,
                        onTriggerEvent: { event in
                            viewModel.onTriggerEvent(stateEvent: event)
                        }
                    )
                    List{
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
                                    destination: RecipeDetailScreen(
                                        recipeId: Int(recipe.id),
                                        cacheModule: self.cacheModule
                                    )
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
                    .background(Color.init(hex: 0xf2f2f2))
                }
                if viewModel.state.isLoading {
                    ProgressView("Searching recipes...")
                }
            }
            .navigationBarHidden(true)
            .alert(isPresented: $viewModel.showDialog, content: {
                let first = viewModel.state.queue.peek()!
                return GenericMessageInfoAlert().build(
                    message: first,
                    onRemoveHeadMessage: {
                        viewModel.onTriggerEvent(stateEvent: RecipeListEvents.OnRemoveHeadMessageFromQueue())
                    }
                )
            })
        }
    }
}

@available(iOS 14.0, *)
struct RecipeListScreen_Previews: PreviewProvider {
    static var previews: some View {
        RecipeListScreen(
            networkModule: NetworkModule(),
            cacheModule: CacheModule()
        )
    }
}
