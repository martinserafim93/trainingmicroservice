package com.user.userservice.service;

import com.user.userservice.model.dto.ProductDTO;
import com.user.userservice.model.dto.ResponseDTO;
import com.user.userservice.model.entity.User;
import com.user.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Value("${base.url.product}")
    private String baseUrl;

    public boolean createUser(User user) {
        if(user != null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<User> readUser() {
        return userRepository.findAll();
    }

    public boolean updateUser(int id, User user) {
        var dataUser = userRepository.findById(id);
        if(dataUser.isPresent()) {
            dataUser.get().setFirstName(user.getFirstName());
            dataUser.get().setLastName(user.getLastName());
            dataUser.get().setUserName(user.getUserName());
            dataUser.get().setEmail(user.getEmail());
            dataUser.get().setPhoneNumber(user.getPhoneNumber());
            dataUser.get().setProductId(user.getProductId());
            userRepository.save(dataUser.get());
            return true;
        }

        return false;
    }

    public boolean deleteUser(int id) {
        var data = userRepository.findById(id);
        if(data.isPresent()) {
            userRepository.delete(data.get());
            return true;
        }
        return false;
    }

    public User readUserById(int id) {
        var user = userRepository.findById(id);
        return user.orElse(null);
    }

    public ResponseDTO getUserProduct(int userId) {
        ResponseDTO result = new ResponseDTO();
        Optional<User> userData = userRepository.findById(userId);
        if(userData.isPresent()) {
            result.setUserName(userData.get().getUserName());
            result.setFirstName(userData.get().getFirstName());
            result.setLastName(userData.get().getLastName());
            result.setEmail(userData.get().getEmail());
        }

        RestTemplate restTemplate = new RestTemplate();
        ProductDTO productDTO = restTemplate.getForObject(baseUrl + "/api/product-by-id?id=" + userData.get().getProductId(),
                ProductDTO.class);

        result.setProductDTO(productDTO);

        return result;

    }

}
