package com.example.project4.Controller;

import com.example.project4.ApiResponse.ApiResponse;
import com.example.project4.Model.Hotel;
import com.example.project4.Service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
@RestController
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/get")
    public ResponseEntity getAllHotel(){
        return ResponseEntity.status(200).body(hotelService.getHotel());
    }

    @PostMapping("/add")
    public ResponseEntity addApartment(@RequestBody Hotel hotel){
        hotelService.addHotel(hotel);
        return ResponseEntity.status(200).body(new ApiResponse("Apartment added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateApartment(@RequestBody Hotel hotel,@PathVariable Integer id){
        hotelService.updateHotel(hotel,id);
        return ResponseEntity.status(200).body(new ApiResponse("Apartment Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteApartment(@PathVariable Integer id){
        hotelService.deleteHotel(id);
        return ResponseEntity.status(200).body(new ApiResponse("Apartment Deleted"));
    }

    @GetMapping("/search-apartment/{apartment}")
    public ResponseEntity searchApartmen(@PathVariable String apartment){
        return ResponseEntity.status(200).body(hotelService.searchApartment(apartment));
    }

    @GetMapping("/discount-only")
    public ResponseEntity getDiscount(){
        return ResponseEntity.status(200).body(hotelService.getAllDiscount());
    }

}
