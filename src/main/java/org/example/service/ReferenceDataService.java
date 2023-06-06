package org.example.service;

import org.example.controller.response.CarBrandsResponse;
import org.example.controller.response.CarModelsResponse;
import org.example.controller.response.CarVersionsResponse;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class ReferenceDataService {

    //BrandId with name
    private Map<Long, String> carBrands;
    //Models by Brand
    private Map<Long, Map<Long, String>> modelsForBrand;
    //Versions by model
    private Map<Long, Map<Long, String>> versionsForModel;


    @PostConstruct
    public void configureCars() {
        carBrands = Map.of(
                1l, "Peugeot",
                2l, "Volkswagen");
        modelsForBrand = Map.of(
                1l, Map.of(1l, "208", 2l, "307"), //peugeot
                2l, Map.of(3l, "Up!") //vokswagen
        );
        versionsForModel = Map.of(
                //208
                1l, Map.of(
                        1l, "Like",
                        2l, "Like Pack",
                        3l, "Active",
                        4l, "Allure",
                        5l, "Feline",
                        6l, "GT"
                ),
                //307
                2l, Map.of(
                        7l, "3Ptas. 2.0 N XSI (143cv)",
                        8l, "4Ptas. 1.6 N XR (110cv)",
                        9l, "4Ptas. 1.6 N XS (110cv)",
                        10l, "4Ptas. 1.6 N XT (110cv)",
                        11l, "4Ptas. 2.0 Hdi XR (90cv)",
                        12l, "4Ptas. 2.0 Hdi XS"),
                // UP!
                3l, Map.of(
                        13l, "Take up!",
                        14l, "High up!",
                        15l, "Cross up! TSI"
                )
        );
    }

    public CarBrandsResponse getBrands() {
        return CarBrandsResponse.builder().brands(carBrands).build();
    }

    public CarModelsResponse getModelsForBrand(Long brandId) {
        Map<Long, String> models = modelsForBrand.get(brandId);
        String brand = carBrands.get(brandId);
        return CarModelsResponse.builder()
                .brand(brand)
                .models(models).build();
    }

    public CarVersionsResponse getVersionsForModel(Long brandId, Long modelId) {
        String brand = carBrands.get(brandId);
        String model = modelsForBrand.get(brandId).get(modelId);
        Map<Long, String> versions = versionsForModel.get(modelId);
        return CarVersionsResponse.builder()
                .brand(brand)
                .model(model)
                .versions(versions)
                .build();
    }
}
