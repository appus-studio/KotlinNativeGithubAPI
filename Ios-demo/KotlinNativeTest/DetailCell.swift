//
//  detailCell.swift
//  KotlinNativeTest
//
//  Created by Andrew Mysyk on 5/24/19.
//  Copyright © 2019 Appus Software. All rights reserved.
//

import UIKit

class DetailCell: UITableViewCell {
    @IBOutlet weak var idLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var urlLabel: UILabel!
    
    func configure(with user: UserDetail) {
        idLabel.text = "\(user.id)"
        nameLabel.text = user.fullName
        descriptionLabel.text = user.userDetailDescription
        urlLabel.text = user.htmlURL
    }
}
