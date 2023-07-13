package com.xloop.carservice;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {

    private ICar repo;

    public CarController(ICar iCar){
        this.repo = iCar;
    }
    
    @PostMapping("/add")
    public String addCar(@RequestBody Car car){
        repo.save(car);
        return "New Car has been Added";
    }

    @GetMapping("/getList")
    public List<Car> getAllCars(){
        return repo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCarById(@PathVariable int id){
        repo.deleteById(id);
    }
}
