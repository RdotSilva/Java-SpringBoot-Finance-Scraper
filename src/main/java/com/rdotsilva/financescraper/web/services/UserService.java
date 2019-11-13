package com.rdotsilva.financescraper.web.services;

import com.rdotsilva.financescraper.web.models.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}