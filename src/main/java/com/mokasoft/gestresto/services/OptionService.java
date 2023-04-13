package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.OptionRequest;
import com.mokasoft.gestresto.dtos.OptionResponse;

import java.util.List;

public interface OptionService {
    OptionResponse saveOption(OptionRequest optionRequest);
    OptionResponse updateOption(OptionRequest optionRequest,Long optionId);
    void deleteOption(Long optionId);
    List<OptionResponse> getAllOptions();
}
