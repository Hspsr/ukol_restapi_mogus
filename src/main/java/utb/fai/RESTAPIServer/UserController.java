package utb.fai.RESTAPIServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser")
    public ResponseEntity<MyUser> getUserById(@RequestParam Long id) {
        MyUser user = userRepository.findById(id).orElse(null);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser newUser) {
        if(!newUser.isUserDataValid()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            try{
                MyUser user = userRepository.save(newUser);
                return new ResponseEntity<>(user, HttpStatus.CREATED); 
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }  
    }

    @PutMapping("/editUser")
    public ResponseEntity<MyUser> editUser(@RequestParam Long id, @RequestBody MyUser editUser){
        MyUser user = userRepository.findById(id).orElse(null);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            user.setName(editUser.getName());
            user.setEmail(editUser.getEmail());
            user.setPhoneNumber(editUser.getPhoneNumber());
            if(editUser.isUserDataValid()){
                return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
        }

    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<MyUser> deleteUser(@RequestParam Long id){
        MyUser user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<MyUser> deleteAllUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
