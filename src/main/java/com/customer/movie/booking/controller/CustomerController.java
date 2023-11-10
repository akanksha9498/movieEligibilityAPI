package com.customer.movie.booking.controller;

import com.customer.movie.booking.Entity.Customer;
import com.customer.movie.booking.model.*;
import com.customer.movie.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer/")
public class CustomerController {
    @Autowired
    CustomerRepository custRepo;    //repository (where the data is saved)
    @PostMapping("check/eligibility")
    public CustomerResponse createCustomerData(@RequestBody CustomerRequest customer){

        try {
            CustomerResponse customerResponse = new CustomerResponse();
            CustomerData customerData = new CustomerData();
            Customer customerEntity = new Customer();


            customerData.setAge(customer.getAge());
            String fullName = customer.getFirstName() + " " + customer.getMiddleName() + " " + customer.getLastName();
            customerData.setName(fullName);
            boolean isEligibleForUnder12 = false;
            int count=0;
            switch (customer.getMovieType().toLowerCase()) {
                case "horror":
                    case "r-rated":
                case "violent":
                    isEligibleForUnder12 = false;
                    break;
                case "comedy":
                case "thriller":
                case "science fiction":
                case "adventure":
                case "documentary":
                case "musical":
                case "romance":
                case "animation":
                    isEligibleForUnder12 = true;
                    break;

                default:
                    count++;
                    isEligibleForUnder12 = false;
                    break;
            }
            if (customer.getAge() < 12 && isEligibleForUnder12==false) {
                customerEntity.setEligible(false);
                customerData.setEligible(false);
                if(count==0) {
                    customerResponse.setMessage("Not Eligible");
                }
                else{
                    customerResponse.setMessage("Enter valid movie type");
                }
            } else {
                customerEntity.setEligible(true);
                customerData.setEligible(true);
                customerResponse.setMessage("Eligible");
            }
            customerResponse.setCustomerData(customerData);
            customerResponse.setCustomerData(customerData);

            return customerResponse;
        }
        catch(Exception ex) {
            System.out.println("Exception occurred" + ex.getMessage());
            return null;
        }



    }
    @PostMapping("register")
    public String registerCustomer(@Valid @RequestBody CustomerRequest customerRequest){
       Customer customerEntity = new Customer();
       customerEntity.setAge(customerRequest.getAge());
       customerEntity.setName(customerRequest.getFirstName()+" "+customerRequest.getMiddleName()+" "+customerRequest.getLastName());
       customerEntity.setAddress(customerRequest.getAddress());
       customerEntity.setGender(customerRequest.getGender());
       customerEntity.setContactNumber(customerRequest.getContactNumber());
       customerEntity.setPinCode(customerRequest.getPinCode());
       custRepo.save(customerEntity);
       String message = "Registration successful for "+ customerEntity.getName()+" with ID : "+customerEntity.getCustomerID();
       return message;
    }
    @DeleteMapping("delete/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        custRepo.deleteById(customerId);
        String message = "customer deleted successfully ";
        return message;
    }


}
