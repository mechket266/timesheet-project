package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

    // ✅ CORRECTION : Jamais retourner null + protection contre NPE
    @Override
    @Transactional(readOnly = true)
    public List<User> retrieveAllUsers() { 
        l.info("Retrieving all users");
        
        List<User> users = userRepository.findAll();
        
        // ✅ Protection : si null, retourner liste vide
        if (users == null) {
            l.warn("userRepository.findAll() returned null, returning empty list");
            return new ArrayList<>();
        }
        
        // ✅ Protection : appeler size() seulement si users != null
        l.info("Found {} users", users.size());
        
        return users;
    }

    @Override
    public User addUser(User u) {
        User utilisateur = null; 
        try {
            l.info("Adding user: {}", u.getFirstName());
            utilisateur = userRepository.save(u); 
            l.info("User added successfully with id: {}", utilisateur.getId());
        } catch (Exception e) {
            l.error("Error in addUser() : " + e.getMessage(), e);
        }
        return utilisateur; 
    }

    @Override 
    public User updateUser(User u) {
        User userUpdated = null; 
        try {
            l.info("Updating user with id: {}", u.getId());
            userUpdated = userRepository.save(u); 
            l.info("User updated successfully");
        } catch (Exception e) {
            l.error("Error in updateUser() : " + e.getMessage(), e);
        }
        return userUpdated; 
    }

    @Override
    public void deleteUser(String id) {
        try {
            l.info("Deleting user with id: {}", id);
            userRepository.deleteById(Long.parseLong(id)); 
            l.info("User deleted successfully");
        } catch (Exception e) {
            l.error("Error in deleteUser() : " + e.getMessage(), e);
        }
    }

    @Override
    public User retrieveUser(String id) {
        User u = null;
        try {
            l.info("Retrieving user with id: {}", id);
            u = userRepository.findById(Long.parseLong(id)).orElse(null);
            if (u != null) {
                l.info("User found: {}", u.getFirstName());
            } else {
                l.warn("User not found with id: {}", id);
            }
        } catch (Exception e) {
            l.error("Error in retrieveUser() : " + e.getMessage(), e);
        }
        return u;
    }
}
