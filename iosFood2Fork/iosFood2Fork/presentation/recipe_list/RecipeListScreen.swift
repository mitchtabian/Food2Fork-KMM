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
    
    private let appModule: AppModule
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(
        appModule: AppModule,
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ) {
        self.appModule = appModule
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(
            appModule: self.appModule,
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategoryUtil: FoodCategoryUtil()
        )
    }
    
    var body: some View {
        NavigationView{
            ZStack{
                VStack{
                    SearchAppBar(viewModel: viewModel)
                    List{
                        ForEach(viewModel.recipes, id: \.self.id){ recipe in
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
                                    destination: RecipeScreen(
                                        recipeId: Int(recipe.id),
                                        appModule: self.appModule,
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
                if viewModel.loading {
                    ProgressView("Searching recipes...")
                }
            }
            .navigationBarHidden(true)
//            .alert(isPresented: $dialogQueue.hasMessages, content: {
//                let first = dialogQueue.queue.peek()!
//                return Alert(
//                    title: Text(first.title),
//                    message: Text(first.description_ ?? "Something went wrong"),
//                    primaryButton: .default(
//                        Text(first.positiveAction?.positiveBtnTxt ?? "Ok"),
//                        action: {
//                            if(first.positiveAction != nil){
//                                first.positiveAction?.onPositiveAction()
//                            }
//                        }
//                    ),
//                    secondaryButton: .default(
//                        Text(first.negativeAction?.negativeBtnTxt ?? "Cancel"),
//                        action: {
//                            if(first.negativeAction != nil){
//                                first.negativeAction?.onNegativeAction()
//                            }
//                        }
//                    )
//                )
//            })
        }
    }
}

@available(iOS 14.0, *)
struct RecipeListScreen_Previews: PreviewProvider {
    static var previews: some View {
        RecipeListScreen(
            appModule: AppModule(),
            networkModule: NetworkModule(),
            cacheModule: CacheModule()
        )
    }
}
