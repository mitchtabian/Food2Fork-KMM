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
    
    @State var query: String
    let onUpdateQuery: (String) -> Void
    let selectedCategory: FoodCategory?
    let onUpdateSelectedCategory: (FoodCategory) -> Void
    let foodCategories: [FoodCategory]
    let onTriggerEvent: (RecipeListEvents) -> Void
    
    init(
        query: String,
        onUpdateQuery: @escaping (String) -> Void,
        selectedCategory: FoodCategory?,
        onUpdateSelectedCategory: @escaping (FoodCategory) -> Void,
        foodCategories: [FoodCategory],
        onTriggerEvent: @escaping (RecipeListEvents) -> Void
    ) {
        self.onUpdateQuery = onUpdateQuery
        self.selectedCategory = selectedCategory
        self.onUpdateSelectedCategory = onUpdateSelectedCategory
        self.foodCategories = foodCategories
        self.onTriggerEvent = onTriggerEvent
        self._query = State(initialValue: query) // set initial value for query
    }
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $query,
                    onCommit:{
                        onTriggerEvent(RecipeListEvents.NewSearch())
                    }
                )
                .onChange(of: query, perform: { value in
                    onUpdateQuery(value)
                })
                
            }
            .padding(.bottom, 8)
            ScrollView(.horizontal){
                HStack(spacing: 10){
                    ForEach(foodCategories, id: \.self){ category in
                        FoodCategoryChip(
                            category: category.value,
                            isSelected: selectedCategory == category
                        )
                        .onTapGesture {
                            query = category.value
                            onUpdateSelectedCategory(category)
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

//@available(iOS 14.0, *)
//struct SearchAppBar_Previews: PreviewProvider {
//    static let dtoMapper = RecipeDtoMapper()
//    static let driverFactory = DriverFactory()
//    static let recipeEntityMapper = RecipeEntityMapper()
//    static let dateUtil = DatetimeUtil()
//    static let recipeService = RecipeServiceImpl(
//        recipeDtoMapper: dtoMapper,
//        httpClient: KtorClientFactory().build(),
//        baseUrl: RecipeServiceImpl.Companion().BASE_URL
//    )
//    static let recipeDatabase = RecipeDatabaseFactory(driverFactory: driverFactory).createDatabase()
//    static let searchRecipes = SearchRecipes(
//        recipeService: recipeService,
//        recipeDatabase: recipeDatabase,
//        recipeEntityMapper: recipeEntityMapper,
//        dateUtil: dateUtil
//    )
//    static let foodCategoryUtil = FoodCategoryUtil()
//    static let viewModel = RecipeListViewModel(
//        searchRecipes: searchRecipes,
//        foodCategoryUtil: foodCategoryUtil
//    )
//    static var previews: some View {
//        SearchAppBar(viewModel: viewModel)
//    }
//}
