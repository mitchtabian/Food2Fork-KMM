//
//  RecipeDetailViewModel.swift
//  iosFood2Fork
//
//  Created by Mitch Tabian on 2021-06-09.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeDetailViewModel: ObservableObject {
    
    // Dependencies
    private let getRecipe: GetRecipe
    
    // State
    @Published var state: RecipeDetailState =  RecipeDetailState()
    
    @Published var showDialog: Bool = false
    
    init(
        recipeId: Int,
        getRecipe: GetRecipe
    ) {
        self.getRecipe = getRecipe
        onTriggerEvent(stateEvent: RecipeDetailEvents.GetRecipe(recipeId: Int32(recipeId)))
    }
    
    func onTriggerEvent(stateEvent: RecipeDetailEvents){
        switch stateEvent {
            case is RecipeDetailEvents.GetRecipe:
                getRecipe(recipeId: Int((stateEvent as! RecipeDetailEvents.GetRecipe).recipeId))
            case is RecipeDetailEvents.OnRemoveHeadMessageFromQueue:
                removeHeadFromQueue()
            default: doNothing()
        }
    }
        
    private func getRecipe(recipeId: Int){
        do{
            try self.getRecipe.execute(
                recipeId: Int32(recipeId)
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false
                    self.updateState(isLoading: loading)
                    
                    if(data != nil){
                        self.updateState(recipe: data! as Recipe)
                        
                        let msg = GenericMessageInfo.Builder()
                            .id(id: String((data! as Recipe).id))
                            .title(title: "Error with \((data! as Recipe).id)")
                            .uiComponentType(uiComponentType: UIComponentType.Dialog())
                            .description(description: "Something went wrong")
                        self.appendToQueue(message: msg.build())
                    }
                    if(message != nil){
                        self.handleMessageByUIComponentType(message!.build())
                    }
                }else{
                    print("GetRecipe: DataState is nil")
                }
            })
        }catch{
            print("GetRecipe: ERROR: \(error)")
        }
    }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        switch message.uiComponentType{
        case UIComponentType.Dialog():
            appendToQueue(message: message)
        case UIComponentType.None():
            print("\(message.description)")
        default:
            doNothing()
        }
    }
    
    private func appendToQueue(message: GenericMessageInfo){
        print("Appending: \(message.id)")
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.queue
        let queueUtil = GenericMessageInfoQueueUtil() // prevent duplicates
        if !queueUtil.doesMessageAlreadyExistInQueue(queue: queue, messageInfo: message) {
           queue.add(element: message)
           updateState(queue: queue)
        }
        shouldShowDialog()
   }
    
    /**
     *  Remove the head message from queue
     */
    private func removeHeadFromQueue(){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.queue
        do {
            try queue.remove()
            updateState(queue: queue)
        }catch{
            print("\(error)")
        }
    }
    
    func shouldShowDialog(){
        let currentState = (self.state.copy() as! RecipeDetailState)
        showDialog = currentState.queue.items.count > 0
    }
        
    private func doNothing(){}
    
    private func updateState(
        isLoading: Bool? = nil,
        recipe: Recipe? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeDetailState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            recipe: recipe ?? currentState.recipe,
            queue: queue ?? currentState.queue
        )
    }
    
}
