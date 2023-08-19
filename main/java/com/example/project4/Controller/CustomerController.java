package com.example.project4.Controller;


import com.example.project4.ApiResponse.ApiResponse;
import com.example.project4.Model.Customer;
import com.example.project4.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getallCustomers(){
        return ResponseEntity.status(200).body(customerService.getCustomer());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@RequestBody Customer customer,@PathVariable Integer id){
        customerService.updateCustomer(customer,id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Deleted"));
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username , @PathVariable String password){
        customerService.login(username,password);
        return ResponseEntity.status(200).body(new ApiResponse("Logged on "));
    }

    @PutMapping("/add-balance/{username}/{balance}")
    public ResponseEntity addBalance(@PathVariable String username,@PathVariable Integer balance){
        customerService.addBalance(username, balance);
        return ResponseEntity.status(200).body(new ApiResponse("Balance added"));
    }

    @PutMapping("/book-hotel/{username}/{apartment}/{duration}")
    public ResponseEntity bookHotel(@PathVariable String username , @PathVariable String apartment , @PathVariable Integer duration){
        customerService.bookHotel(username, apartment, duration);
        return ResponseEntity.status(200).body(new ApiResponse("done"));
    }

    @PutMapping("/duration-done/{username}/{apartment}")
    public ResponseEntity durationDone(@PathVariable String username,@PathVariable String apartment){
        customerService.durationDone(username, apartment);
        return ResponseEntity.status(200).body(new ApiResponse("Your duration in this hotel is done"));
    }

    @GetMapping("/get-booked")
    public ResponseEntity getOnlyBooked(){
        return ResponseEntity.status(200).body(customerService.getOnlyBooked());
    }

}
