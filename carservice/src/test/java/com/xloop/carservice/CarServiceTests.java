package com.xloop.carservice;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
public class CarServiceTests{
    
    @Autowired
	private MockMvc mvc;

    @Mock
    private ICar repo;

    @InjectMocks
    private CarController carController;

    private JacksonTester<Car> jsonCar;
    private JacksonTester<List<Car>> jsonCars;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
	void contextLoads() {
	}

    @Test
    public void getAddCar() throws Exception{
        Car car = new Car("Mehran", "I am a car", "I am an old small Car of having place of only 4 people", "image", 230000);

        when(repo.save(car)).thenReturn(car);

        mvc.perform(MockMvcRequestBuilders
				.post("/car/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCar
						.write(car)
						.getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAlltheCars() throws Exception{
        Car car1 = new Car("Mehran", "I am a car", "I am an old small Car of having place of only 4 people", "image", 230000);
        Car car2 = new Car("Mehran", "I am a car", "I am an old small Car of having place of only 4 people", "image", 230000);
        
        List<Car> carList = new ArrayList<>();
		carList.add(car1);
		carList.add(car2);

        when(repo.findAll()).thenReturn(carList);

        mvc.perform(MockMvcRequestBuilders
				.get("/car/getList")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonCars.write(carList).getJson()));        

    }

    @Test
    public void getCarById() throws Exception{
        Car car1 = new Car("Mehran", "I am a car", "I am an old small Car of having place of only 4 people", "image", 230000);

        List<Car> newcarList = new ArrayList<>();
        newcarList.add(car1);

        when(repo.findById(1L)).thenReturn(Optional.of(car1));

        mvc.perform(MockMvcRequestBuilders
				.get("/car/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonCar.write(car1).getJson()));


    }
}
