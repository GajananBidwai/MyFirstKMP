//
//  ArticleScreen.swift
//  iosApp
//
//  Created by Neosoft on 10/05/26.
//

import SwiftUI
import Shared

extension ArticlesScreen {
    
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articleViewModel: ArticlesViewModel
        
        init() {
            articleViewModel = ArticlesViewModel()
            articleState = articleViewModel.articleState.value as! ArticlesState
        }
        
        @Published var articleState: ArticlesState
        
//        func startObserving() {
//            Task {
//                for await articles in articleViewModel.articleState {
//                    self.articleState = articles
//                }
//            }
//        }
        
        func startObserving() {
            articleViewModel.observeArticlesState { state in
                
                if let state = state as? ArticlesState {
                    DispatchQueue.main.async {
                        self.articleState = state
                    }
                } else {
                    print("Type casting failed")
                }
            }
            
            
            
        }
    }
}

struct ArticlesScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            if viewModel.articleState.isLoading {
                Loader()
            }
            
            if let error = viewModel.articleState.error {
                ErrorMessage(message: error)
            }
            
            if (!viewModel.articleState.articles.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articleState.articles, id: \.self) { articles in
                            ArticleItemView(article: articles)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
        
        
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}

struct ArticleItemView: View {
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image load error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
                
            
            
        }
        .padding(16)
    }
}
