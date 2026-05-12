import SwiftUI
import Shared

struct ContentView: View {
    @State private var showOpenAbout = false
    
    var body: some View {
        let articleScreen = ArticlesScreen(viewModel: .init())
        
        VStack {
            NavigationStack {
                ArticlesScreen(viewModel: .init())
                    .toolbar {
                        ToolbarItem {
                            Button {
                                showOpenAbout = true
                            } label: {
                                Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                            }
                            .popover(isPresented: $showOpenAbout) {
                                AboutScreen()
                            }
                        }
                    }
            }
            .refreshable {
                articleScreen.viewModel.articleViewModel.getArticles(forceFetch: true)
            }
        }
        
    }
}

