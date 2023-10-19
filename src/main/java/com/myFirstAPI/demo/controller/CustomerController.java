package com.myFirstAPI.demo.controller;

import com.myFirstAPI.demo.Entity.Customer;
import com.myFirstAPI.demo.model.CustomerData;
import com.myFirstAPI.demo.model.CustomerRequest;
import com.myFirstAPI.demo.model.CustomerResponse;
import com.myFirstAPI.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            boolean isEligibleForUnder18 = false;
            int count=0;
            switch (customer.getMovieType().toLowerCase()) {
                case "horror":
                case "thriller":
                case "r-rated":
                case "violent":
                    isEligibleForUnder18 = false;
                    break;
                case "comedy":
                case "science fiction":
                case "adventure":
                case "documentary":
                case "musical":
                case "romance":
                    isEligibleForUnder18 = true;
                    break;

                default:
                    count++;
                    isEligibleForUnder18 = false;
                    break;
            }
            if (customer.getAge() < 18 && isEligibleForUnder18==false) {
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


            customerEntity.setName(fullName);
            customerEntity.setAge(customer.getAge());
            custRepo.save(customerEntity);   // it will save the data to the repo.

            return customerResponse;
        }
        catch(Exception ex) {
            System.out.println("Exception occurred" + ex.getMessage());
            return null;
        }



    }
    @PostMapping("register")
    public CustomerRequest registerCustomer(@RequestBody CustomerRequest customerRequest){
       Customer customerEntity = new Customer();
       customerEntity.setAge(customerRequest.getAge());
       customerEntity.setName(customerRequest.getFirstName()+" "+customerRequest.getMiddleName()+" "+customerRequest.getLastName());
       custRepo.save(customerEntity);
       return customerRequest;
    }

}
