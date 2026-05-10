//
//  AboutScreen.swift
//  iosApp
//
//  Created by Neosoft on 10/05/26.
//

import SwiftUI

struct AboutScreen: View {
    @Environment(\.dismiss) private var dismiss
    var body: some View {
        NavigationStack {
            ListView()
                .navigationTitle("About Device")
                .toolbar {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }

                    
                }
        }
    }
}

#Preview {
    AboutScreen()
}
