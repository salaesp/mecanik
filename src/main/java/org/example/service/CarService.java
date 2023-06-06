package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.CarRelationshipEntity;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.CarMapper;
import org.example.model.Car;
import org.example.model.CarRelationship;
import org.example.repository.CarRelationshipRepository;
import org.example.utils.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRelationshipRepository relationshipRepository;
    private final CarMapper mapper;
    private final UserService userService;

    public Car createCarOnUser(Car car) {
        CarRelationship relationship = CarRelationship.builder()
                .user(userService.getUser())
                .car(car)
                .build();
        CarRelationship save = internalSave(relationship);
        return save.getCar();
    }


    public Car getCarById(Long id) {
        return this.getCarById(id, false);
    }

    public Car getCarById(Long id, boolean includeDeleted) {
        return internalGet(id, includeDeleted).getCar();
    }

    public List<Car> listCars(boolean includeDeleted) {
        List<CarRelationshipEntity> byUserId = includeDeleted ?
                relationshipRepository.findByUserId(ContextUtils.getUserId()) :
                relationshipRepository.findByUserIdAndDeletedFalse(ContextUtils.getUserId());
        return byUserId
                .stream()
                .map(CarRelationshipEntity::getCar)
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    public Car deleteCar(Long carId) {
        CarRelationship relationship = this.internalGet(carId, false);
        relationship.setDeleted(true);
        relationship.getCar().setDeleted(true);
        return internalSave(relationship).getCar();
    }

    private CarRelationship internalSave(CarRelationship relationship) {
        return mapper.toModel(relationshipRepository.save(mapper.toEntity(relationship)));
    }

    private CarRelationship internalGet(Long carId, boolean includeDeleted) {
        Optional<CarRelationshipEntity> byId = includeDeleted ?
                relationshipRepository.findByUserIdAndCarId(ContextUtils.getUserId(), carId) :
                relationshipRepository.findByUserIdAndCarIdAndDeletedFalse(ContextUtils.getUserId(), carId);
        return mapper.toModel(byId.orElseThrow(() -> new ResourceNotFoundException(carId)));
    }
}
