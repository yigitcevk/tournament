package com.dream_tournament.service;

import com.dream_tournament.dto.request.CreateUserRequest;
import com.dream_tournament.dto.response.CreateUserResponse;
import com.dream_tournament.dto.request.UpdateLevelRequest;
import com.dream_tournament.dto.response.UpdateLevelResponse;
import com.dream_tournament.model.User;
import com.dream_tournament.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param request the create user request: username
     * @return the response containing user details: id, country, level, coins
     */
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + request.getUsername());
        }

        User user = new User(
                request.getUsername()
        );
        User savedUser = userRepository.save(user);

        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getCountry(),
                savedUser.getLevel(),
                savedUser.getCoins()
        );
    }

    /**
     * Updates the user's level and coins.
     *
     * @param request the update level request: userId
     * @return the response containing updated level and coins: level, coins
     */
    public UpdateLevelResponse updateLevel(UpdateLevelRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + request.getUserId()));

        user.setLevel(user.getLevel() + 1);
        user.setCoins(user.getCoins() + 25);
        User updatedUser = userRepository.save(user);

        return new UpdateLevelResponse(
                updatedUser.getLevel(),
                updatedUser.getCoins()
        );
    }
}
