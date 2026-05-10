import SwiftUI
import Shared

struct ContentView: View {
    @State private var showOpenAbout = false
    
    var body: some View {
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
        }
        
    }
}

