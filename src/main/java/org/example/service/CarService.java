package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CarDto;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.CarMapper;
import org.example.entity.CarEntity;
import org.example.model.Car;
import org.example.repository.CarRepository;
import org.example.utils.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final CarMapper mapper;

    public Car createCar(Car car) {
        CarEntity carEntity = mapper.toEntity(car);
        carEntity.setUserId(ContextUtils.getUserId());
        CarEntity save = internalSave(carEntity);
        return mapper.toModel(save);
    }


    public Car getCarById(Long id) {
        return this.getCarById(id, false);
    }

    public Car getCarById(Long id, boolean includeDeleted) {
        return mapper.toModel(internalGet(id, includeDeleted));
    }

    public List<Car> listCars(boolean includeDeleted) {
        List<CarEntity> byUserId = includeDeleted ?
                repository.findByUserId(ContextUtils.getUserId()) :
                repository.findByUserIdAndDeletedFalse(ContextUtils.getUserId());
        return byUserId
                .stream().map(mapper::toModel)
                .collect(Collectors.toList());
    }

    public Car deleteCar(Long id) {
        CarEntity carEntity = this.internalGet(id, false);
        carEntity.setDeleted(true);
        return mapper.toModel(internalSave(carEntity));
    }

    private CarEntity internalSave(CarEntity carEntity) {
        return repository.save(carEntity);
    }

    private CarEntity internalGet(Long id, boolean includeDeleted) {
        Optional<CarEntity> byId = includeDeleted ?
                repository.findByUserIdAndId(ContextUtils.getUserId(), id) :
                repository.findByUserIdAndIdAndDeletedFalse(ContextUtils.getUserId(), id);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return byId.get();
    }
}
