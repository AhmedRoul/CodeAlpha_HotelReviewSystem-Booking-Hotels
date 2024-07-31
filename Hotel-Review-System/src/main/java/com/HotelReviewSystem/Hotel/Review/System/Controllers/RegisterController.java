package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Config.Password.CustomPasswordEncoder;
import com.HotelReviewSystem.Hotel.Review.System.Model.Customer;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import com.HotelReviewSystem.Hotel.Review.System.Repository.CustomerRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.UserRepository;
import com.HotelReviewSystem.Hotel.Review.System.Util.Countrys;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.RegisterRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.RegisterResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class RegisterController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?>SignUp(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            RegisterResponse registerResponse =  RegisterResponse.builder().build();
            return ResponseEntity.badRequest().body(SaveError(registerResponse,bindingResult));
        }


        if(userRepository.existsByEmail(registerRequest.getEmail())){

            RegisterResponse registerResponse = RegisterResponse.builder().email("Email already exists").build();
           return ResponseEntity.badRequest().body(registerResponse);
        }
        if(!Countrys.countries.contains( registerRequest.getNationality()))
        {
            RegisterResponse registerResponse = RegisterResponse.builder().nationality("Nationality not correct").build();
            return  ResponseEntity.badRequest().body(registerResponse);
        }
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

       User user=userRepository.save(registerRequest.GetUser());
       Customer customer= registerRequest.GetCustomer();
       customer.setUser(user);
       Customer customer1= customerRepository.save(customer);

        return ResponseEntity.ok().body("redirect to login page ");
    }
    private RegisterResponse  SaveError(RegisterResponse registerResponse,BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();


            switch (fieldName) {
                case "email" -> registerResponse.setEmail(errorMessage);
                case "password" -> registerResponse.setPassword(errorMessage);
                case "nationality" -> registerResponse.setNationality(errorMessage);
                case "firstname" -> registerResponse.setFirstname(errorMessage);
                case "lastname" -> registerResponse.setLastname(errorMessage);
                case "birthday" -> registerResponse.setBirthday(errorMessage);
                case "phoneNumber" -> registerResponse.setPhoneNumber(errorMessage);
                case "address" -> registerResponse.setAddress(errorMessage);
            }

        }
        return registerResponse;
    }
}
