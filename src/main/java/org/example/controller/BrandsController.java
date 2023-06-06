package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.controller.response.CarBrandsResponse;
import org.example.controller.response.CarModelsResponse;
import org.example.controller.response.CarVersionsResponse;
import org.example.service.ReferenceDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandsController {

    private final ReferenceDataService service;

    @GetMapping
    @Operation(summary = "Lists all brands")
    public CarBrandsResponse getBrands() {
        return service.getBrands();
    }


    @GetMapping("/{brandId}/models")
    @Operation(summary = "Lists all models for brand")
    public CarModelsResponse getModelForBrand(@PathVariable Long brandId) {
        return service.getModelsForBrand(brandId);
    }


    @GetMapping("/{brandId}/models/{modelId}")
    @Operation(summary = "Lists all versions for model")
    public CarVersionsResponse gerVersionsForModel(@PathVariable Long brandId, @PathVariable Long modelId) {
        return service.getVersionsForModel(brandId, modelId);
    }
}
