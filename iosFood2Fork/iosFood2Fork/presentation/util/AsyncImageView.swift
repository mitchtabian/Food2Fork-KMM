// Source:
// https://www.youtube.com/watch?v=volfJt7mupo

import SwiftUI

struct AsyncImageView: View {
    
    @ObservedObject var imageLoader: ImageLoader
    
    init(urlString: String?) {
        imageLoader = ImageLoader(urlString: urlString)
    }
    
    var body: some View {
        Image(uiImage: imageLoader.image ?? AsyncImageView.placeHolder!)
                .resizable()
    }
    
    static var placeHolder = UIImage(named: "white_background")
}

struct URLImageView_Previews: PreviewProvider {
    static var previews: some View {
        AsyncImageView(urlString: nil)
    }
}
