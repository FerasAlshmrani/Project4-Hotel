package com.example.project4.Service;

import com.example.project4.ApiResponse.ApiException;
import com.example.project4.Model.Hotel;
import com.example.project4.Repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> getHotel(){
        return hotelRepository.findAll();
    }

    public void addHotel(Hotel hotel){
        hotelRepository.save(hotel);
    }

    public void updateHotel(Hotel hotel,Integer id){

        Hotel oldHotel = hotelRepository.getHotelById(id);

        if(oldHotel == null) {
            throw new ApiException("Wrong id");
        }

        oldHotel.setApartment(hotel.getApartment());
        oldHotel.setFloorNumber(hotel.getFloorNumber());
        oldHotel.setRooms(hotel.getRooms());
        oldHotel.setIsBooked(hotel.getIsBooked());
        oldHotel.setByUsername(hotel.getByUsername());
        oldHotel.setPrice(hotel.getPrice());

        hotelRepository.save(oldHotel);
    }

    public void deleteHotel(Integer id){
        Hotel find = hotelRepository.getHotelById(id);
        if(find == null){
            throw new ApiException("wrong id");
        }
        hotelRepository.delete(find);
    }

    public Hotel searchApartment(String apartment){
        if(hotelRepository.searchByApartment(apartment) == null){
            throw new ApiException("wrong Apartment name");
        }
        return hotelRepository.searchByApartment(apartment);
    }

    public List<Hotel> getAllDiscount(){
        List<Hotel> hotel = hotelRepository.getHotelByDiscount();
        return hotel;
    }


}
