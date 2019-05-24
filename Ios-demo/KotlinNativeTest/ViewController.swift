//
//  ViewController.swift
//  KotlinNativeTest
//
//  Created by Andrew Mysyk on 5/24/19.
//  Copyright © 2019 Appus Software. All rights reserved.
//

import UIKit
import main

class ViewController: UIViewController {

    @IBOutlet weak var inputTextField: UITextField!
    @IBOutlet weak var tableView: UITableView!
    
    let api = UserSyncRepository()
    
    var dataSource = [UserDetail]() {
        didSet {
            tableView.reloadData()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        inputTextField.delegate = self
    }

}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataSource.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as? DetailCell else { fatalError("CHECK CELL TYPE") }
        cell.configure(with: dataSource[indexPath.row])
        return cell
    }
}

extension ViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        api.getUserRepositories(login: textField.text ?? "") { [weak self] result -> KotlinUnit in
            let data = result.data(using: .utf8)!
            do {
                let users = try? JSONDecoder().decode([UserDetail].self, from: data)
                if let users = users {
                    self?.dataSource.insert(contentsOf: users, at: 0)
                }
            } catch let error as NSError { print(error) }
            return KotlinUnit()
        }
        return true
    }
}
