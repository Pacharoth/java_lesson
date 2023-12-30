package com.ecommerce.ecommerce.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public User createUser(UserRequest UserRequest) {
        User User = new User(UserRequest.getName(), UserRequest.getGender());
        return UserRepository.save(User);
    }

    public User updateUser(Integer id, UserRequest UserRequest) {
        // select * from Users where id=1;
        Optional<User> searchingUser = UserRepository.findById(id);
        User foundedUser = searchingUser.get();
        foundedUser.updateUser(id,
                UserRequest.getName(),
                UserRequest.getGender());
        return UserRepository.save(foundedUser);
    }

    public List<User> getAll() {
        return UserRepository.findAll();
    }

    public boolean deleteUser(Integer id) {
        if (UserRepository.existsById(id)) {
            UserRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
