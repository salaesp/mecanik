package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dto.CarDto;
import org.example.service.CarService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    @PostMapping
    @Operation(summary = "Create a new car for the logged user")
    public CarDto createCar(@RequestBody @Validated CarDto car) {
        return service.createCar(car);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a car by car id")
    public CarDto getCarById(@PathVariable long id, @RequestParam(value = "include_deleted",
            required = false, defaultValue = "false") boolean includeDeleted) {
        return service.getCarById(id, includeDeleted);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a car by id")
    public CarDto deleteCar(@PathVariable long id) {
        return service.deleteCar(id);
    }


    @GetMapping
    @Operation(summary = "List all user's cars")
    public List<CarDto> listCars(@RequestParam(value = "include_deleted",
            required = false, defaultValue = "false") boolean includeDeleted) {
        return service.listCars(includeDeleted);
    }
}
