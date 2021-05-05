//
//  SearchAppBar.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-24.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 14.0, *)
struct SearchAppBar: View {
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(viewModel: RecipeListViewModel){
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $viewModel.query,
                    onCommit:{
                        viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NewSearch())
                    }
                )
                .onChange(of: viewModel.query, perform: { value in
                    viewModel.setQuery(query: value)
                })
                
            }
            .padding(.bottom, 8)
            ScrollView(.horizontal){
                HStack(spacing: 10){
                    ForEach(viewModel.categories, id: \.self){ category in
                        FoodCategoryChip(
                            category: category.value,
                            isSelected: viewModel.selectedCategory == category
                        )
                        .onTapGesture {
                            viewModel.onSelectedCategoryChanged(category: category.value)
                            viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NewSearch())
                        }
                    }
                }
            }
        }
        .padding(.top, 8)
        .padding(.leading, 8)
        .padding(.trailing, 8)
        .background(Color.white.opacity(0))
    }
}

@available(iOS 14.0, *)
struct SearchAppBar_Previews: PreviewProvider {
    static let dtoMapper = RecipeDtoMapper()
    static let driverFactory = DriverFactory()
    static let recipeEntityMapper = RecipeEntityMapper()
    static let dateUtil = DatetimeUtil()
    static let recipeService = RecipeServiceImpl(
        recipeDtoMapper: dtoMapper,
        httpClient: KtorClientFactory().build(),
        baseUrl: RecipeServiceImpl.Companion().BASE_URL
    )
    static let recipeDatabase = RecipeDatabaseFactory(driverFactory: driverFactory).createDatabase()
    static let searchRecipes = SearchRecipes(
        recipeService: recipeService,
        recipeDatabase: recipeDatabase,
        recipeEntityMapper: recipeEntityMapper,
        dateUtil: dateUtil
    )
    static let foodCategoryUtil = FoodCategoryUtil()
    static let viewModel = RecipeListViewModel(
        searchRecipes: searchRecipes,
        foodCategoryUtil: foodCategoryUtil
    )
    static var previews: some View {
        SearchAppBar(viewModel: viewModel)
    }
}
