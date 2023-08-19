package com.example.project4.Repository;

import com.example.project4.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    Hotel getHotelById(Integer id);
    Hotel searchByApartment(String apartment);

    @Query("select h from Hotel h where h.discount< 0 ")
    List<Hotel> getHotelByDiscount();

}