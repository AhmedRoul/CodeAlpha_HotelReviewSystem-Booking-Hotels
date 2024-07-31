package com.HotelReviewSystem.Hotel.Review.System.Controllers;

import com.HotelReviewSystem.Hotel.Review.System.Model.Hotel;
import com.HotelReviewSystem.Hotel.Review.System.Model.HotelType;
import com.HotelReviewSystem.Hotel.Review.System.Model.Room;
import com.HotelReviewSystem.Hotel.Review.System.Repository.HotelRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.HotelTypeRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.RoomRepository;
import com.HotelReviewSystem.Hotel.Review.System.Repository.RoomTypeRepository;
import com.HotelReviewSystem.Hotel.Review.System.dto.Request.AddHotelRequest;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.HotelResponse;
import com.HotelReviewSystem.Hotel.Review.System.dto.Resopose.RegisterResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/Hotel")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelTypeRepository hotelTypeRepository;

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomTypeRepository roomTypeRepository;

    @PostMapping
    @RolesAllowed("Admin")
    public ResponseEntity<?> AddHotel(@Valid @RequestBody AddHotelRequest addHotelRequest,  BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(SaveError(bindingResult));
        }
        Hotel hotel=addHotelRequest.getHotel();

        hotel=hotelRepository.save(hotel);
        return  ResponseEntity.ok(hotel);
        //سششس
    }
    private HotelResponse SaveError(BindingResult bindingResult) {
        HotelResponse hotelResponse=HotelResponse.builder().build();

        for (ObjectError error : bindingResult.getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();


            switch (fieldName) {
                case "name" -> hotelResponse.setName(errorMessage);
                case "government" -> hotelResponse.setGovernment(errorMessage);
                case "city" -> hotelResponse.setCity(errorMessage);
                case "address" -> hotelResponse.setAddress(errorMessage);
                case "idpoint" -> hotelResponse.setIdpoint(errorMessage);
                case "urlLocation" -> hotelResponse.setUrlLocation(errorMessage);

            }

        }
        return hotelResponse;
    }
}
