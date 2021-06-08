//
//  RecipeCard.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-22.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeCard: View {
    
    let recipe: Recipe
    
    init(recipe: Recipe) {
        self.recipe = recipe
    }
    
    var body: some View {
        VStack(alignment: .leading){
            WebImage(url: URL(string: recipe.featuredImage))
                        .resizable()
                        .placeholder(Image(systemName: "photo")) // Placeholder Image
                        .placeholder {
                            Rectangle().foregroundColor(.white)
                        }
                        .indicator(.activity)
                        .transition(.fade(duration: 0.5))
                        .scaledToFill() // 1
                        .frame(height: 250, alignment: .center) // 2
                        .clipped() // 3
                
            HStack(alignment: .lastTextBaseline){
                DefaultText(recipe.title, size: 19)
                    .font(.body)
                    .frame(alignment: .center)
                
                Spacer()
                
                DefaultText(String(recipe.rating))
                    .frame(alignment: .trailing)
            }
            .padding(.top, 8)
            .padding(.leading, 8)
            .padding(.trailing, 8)
            .padding(.bottom, 12)
        }
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 5)
        
    }
}

struct RecipeCard_Previews: PreviewProvider {
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
        dateAdded: DatetimeUtil().now(),
        dateUpdated: DatetimeUtil().now()
    )
    static var previews: some View {
        RecipeCard(recipe: recipe)
    }
}
