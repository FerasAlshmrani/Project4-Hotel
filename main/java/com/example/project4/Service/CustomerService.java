package com.example.project4.Service;


import com.example.project4.ApiResponse.ApiException;
import com.example.project4.Model.Customer;
import com.example.project4.Model.Hotel;
import com.example.project4.Repository.CustomerRepository;
import com.example.project4.Repository.HotelRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;

    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer,Integer id){

        Customer oldCustomer = customerRepository.getCustomerById(id);

        if(oldCustomer == null) {
            throw new ApiException("Wrong id");
        }

        oldCustomer.setName(customer.getName());
        oldCustomer.setUsername(customer.getUsername());
        oldCustomer.setPassword(customer.getPassword());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setBalance(customer.getBalance());
        oldCustomer.setIsBooked(customer.getIsBooked());
        oldCustomer.setWhichApartment(customer.getWhichApartment());
        oldCustomer.setDuration(customer.getDuration());

        customerRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer id){
        Customer find = customerRepository.getCustomerById(id);
        if(find == null){
            throw new ApiException("wrong id");
        }
        customerRepository.delete(find);
    }

    public Customer login(String username , String password){
        Customer find = customerRepository.getCustomerByUsernameAndPassword(username,password);
        if(find == null){
            throw new ApiException("username or password is wrong");
        }
        return find;
    }

    public void addBalance(String username,Integer balance){
        Customer user = customerRepository.searchCustomerByUsername(username);
        if(user == null){
            throw new ApiException("Wrong username");
        }
        user.setBalance(user.getBalance()+balance);
        customerRepository.save(user);
    }

    public void bookHotel (String username, String apartment,Integer duration){
        Customer customer = customerRepository.searchCustomerByUsername(username);

        if(customer == null){
            throw new ApiException("Wrong username");
        }

        Hotel hotel = hotelRepository.searchByApartment(apartment);

        if(hotel == null){
            throw new ApiException("Wrong Apartment");
        }

        if(customer.getIsBooked()){
            throw new ApiException("this user is already booked");
        }
        if(hotel.getIsBooked()){
            throw new ApiException("this Apartment is already booked");
        }

        if(hotel.getDiscount() == 0) {
            if (customer.getBalance() < hotel.getPrice() * duration) {
                throw new ApiException("you dont have much money");
            }

            customer.setBalance(customer.getBalance()-hotel.getPrice()*duration);
        } else{
            if (customer.getBalance() < (hotel.getPrice()*(hotel.getDiscount())/100)*duration) {
                throw new ApiException("you dont have much money");
            }
            customer.setBalance((Integer) customer.getBalance()-(hotel.getPrice()*(hotel.getDiscount())/100)*duration);
        }

        customer.setIsBooked(true);
        customer.setDuration(duration);
        customer.setWhichApartment(hotel.getApartment());

        hotel.setIsBooked(true);
        hotel.setByUsername(customer.getUsername());

        customerRepository.save(customer);
        hotelRepository.save(hotel);

    }

    public void durationDone(String username, String apartment){
        Customer customer = customerRepository.searchCustomerByUsername(username);

        if(customer == null){
            throw new ApiException("Wrong username");
        }

        Hotel hotel = hotelRepository.searchByApartment(apartment);

        if(hotel == null){
            throw new ApiException("Wrong Apartment");
        }

        if(customer.getIsBooked() == false){
            throw new ApiException("this user dont have book yet to remove");
        }
        if(hotel.getIsBooked() == false){
            throw new ApiException("this Apartment dont have book yet to remove");
        }

        customer.setIsBooked(false);
        customer.setDuration(0);
        customer.setWhichApartment("");

        hotel.setIsBooked(false);
        hotel.setByUsername("");

        customerRepository.save(customer);
        hotelRepository.save(hotel);

    }

    public List<Customer> getOnlyBooked(){
        return customerRepository.getCustomerByIsBooked();
    }




}
