//
//  SearchAppBar.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-06-08.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    
    @State var query: String
    let onTriggerEvent: (RecipeListEvents) -> Void
    
    init(
        query: String,
        onTriggerEvent: @escaping (RecipeListEvents) -> Void
    ) {
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
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(query: value))
                    })
                }
                .padding(.bottom, 8)
            }
            .padding(.top, 8)
            .padding(.leading, 8)
            .padding(.trailing, 8)
            .background(Color.white.opacity(0))
        }
}

//struct SearchAppBar_Previews: PreviewProvider {
//    static var previews: some View {
//        SearchAppBar()
//    }
//}
