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
    
    let recipe: Recipe
    
    init(recipe: Recipe) {
        self.recipe = recipe
    }
    
    var body: some View {
        ScrollView{
            VStack(alignment: .leading){
                
                AsyncImageView(urlString: recipe.featuredImage)
                    .scaledToFill()
                    .frame(maxHeight: 250)
                    .clipped()
                
                VStack(alignment: .leading){
                    
                    HStack(alignment: .lastTextBaseline){
                        DefaultText("Updated \(recipe.dateUpdated) by \(recipe.publisher)")
                            .foregroundColor(Color.init(hex: 0x5f5f5f))

                        Spacer()
                        
                        DefaultText(String(recipe.rating))
                            .frame(alignment: .trailing)
                    }
                    
                    ForEach(recipe.ingredients as Array<String>, id: \.self){ ingredient in
                        DefaultText(ingredient)
                            .padding(.top, 4)
                    }
                }
                .background(Color.white)
                .padding(12)
            }
        }
        .navigationBarTitle(recipe.title)
    }
}

struct RecipeScreen_Previews: PreviewProvider {
    static let recipe = Recipe(
        id: 1,
        title: "Slow Cooker Beef and Barley Soup",
        publisher: "jessica",
        featuredImage: "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/1551/featured_image.png",
        rating: 99,
        sourceUrl: "http://picky-palate.com/2013/01/14/slow-cooker-beef-and-barley-soup/",
        ingredients: [
            "8.8 ounces barley",
            "1 cup chopped celery",
            "1 pound stewing beef",
            "1 teaspoon kosher salt",
            "1 1/2 cups chopped onion",
            "1/2 teaspoon kosher salt",
            "1/2 cup all-purpose flour",
            "1 small jalapeno (optional)",
            "3 tablespoons minced garlic",
            "1/4 cup chopped fresh parsley",
            "2 cups sliced carrots, peeled",
            "2 cups sliced cremini mushrooms",
            "1/2 teaspoon ground black pepper",
            "64 ounces reduced sodium beef broth",
            "2-3 tablespoons Worcestershire Sauce",
            "3 tablespoons extra virgin olive oil",
            "1 medium zucchini, sliced and chopped",
            "1/2 teaspoon freshly ground black pepper",
            "2-3 tablespoons fresh chopped thyme leaves"
        ],
        dateAdded: DateUtil().now(),
        dateUpdated: DateUtil().now()
    )
    static var previews: some View {
        RecipeScreen(recipe: recipe)
    }
}
