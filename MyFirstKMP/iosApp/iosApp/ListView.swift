//
//  ListView.swift
//  iosApp
//
//  Created by Neosoft on 10/05/26.
//

import SwiftUI
import Shared

struct ListView: View {
    
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let item: [RowItem] = {
        let platForm = Platform()
        platForm.logSystemInfo()
        
        var result: [RowItem] = [
            .init(title: "Operating System",
                  subtitle: "\(platForm.osName) \(platForm.osVersion)"),
            .init(title: "Device", subtitle: platForm.deviceModel),
            .init(title: "Density", subtitle: "@\(platForm.density)x")
        ]
        
        
        return result
        
    }()
    
    
    
    var body: some View {
        List {
            ForEach(item, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                    
                    Text(item.subtitle)
                        .font(.body)
                        .foregroundStyle(.primary)
                }
                .padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    ListView()
}
