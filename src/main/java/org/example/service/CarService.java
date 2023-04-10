package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CarDto;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.CarMapper;
import org.example.entity.CarEntity;
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

    public CarDto createCar(CarDto car) {
        CarEntity carEntity = mapper.toEntity(car);
        carEntity.setUserId(ContextUtils.getUserId());
        CarEntity save = internalSave(carEntity);
        return mapper.toDto(save);
    }


    public CarDto getCarById(Long id) {
        return this.getCarById(id, false);
    }

    public CarDto getCarById(Long id, boolean includeDeleted) {
        return mapper.toDto(internalGet(id, includeDeleted));
    }

    public List<CarDto> listCars(boolean includeDeleted) {
        List<CarEntity> byUserId = includeDeleted ?
                repository.findByUserId(ContextUtils.getUserId()) :
                repository.findByUserIdAndDeletedFalse(ContextUtils.getUserId());
        return byUserId
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto deleteCar(Long id) {
        CarEntity carEntity = this.internalGet(id, false);
        carEntity.setDeleted(true);
        return mapper.toDto(internalSave(carEntity));
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
