import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private let networkModule = NetworkModule()
    private let cacheModule = CacheModule()
    
    var body: some Scene {
        WindowGroup {
            RecipeListScreen(
                networkModule: networkModule,
                cacheModule: cacheModule
            )
        }
    }
}
