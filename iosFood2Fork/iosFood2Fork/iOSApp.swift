import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private let appModule = AppModule()
    private let networkModule = NetworkModule()
    private let cacheModule = CacheModule()
    
    var body: some Scene {
        WindowGroup {
            RecipeListScreen(
                appModule: appModule,
                networkModule: networkModule,
                cacheModule: cacheModule
            )
        }
    }
}
