//
//  Model.swift
//  KotlinNativeTest
//
//  Created by Andrew Mysyk on 5/24/19.
//  Copyright © 2019 Appus Software. All rights reserved.
//

import Foundation

class UserDetail: Codable {
    let id: Int
    let fullName: String
    let htmlURL: String
    let userDetailDescription: String?
    
    enum CodingKeys: String, CodingKey {
        case id
        case fullName = "full_name"
        case htmlURL = "html_url"
        case userDetailDescription = "description"
    }
    
    init(id: Int, fullName: String, htmlURL: String, userDetailDescription: String) {
        self.id = id
        self.fullName = fullName
        self.htmlURL = htmlURL
        self.userDetailDescription = userDetailDescription
    }
}
