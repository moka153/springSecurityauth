package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.OptionRequest;
import com.mokasoft.gestresto.dtos.OptionResponse;
import com.mokasoft.gestresto.entities.Option;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.OptionMapper;
import com.mokasoft.gestresto.repositories.OptionRepository;
import com.mokasoft.gestresto.services.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;
    @Override
    public OptionResponse saveOption(OptionRequest optionRequest) {
        try {
            Option option = optionMapper.optionRequestToOption(optionRequest);
            Option savedOption = optionRepository.save(option);
            return optionMapper.optionToOptionResponse(savedOption);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("option already exists");
        }
    }

    @Override
    public OptionResponse updateOption(OptionRequest optionRequest, Long optionId) {
        try {
            Option option = optionMapper.optionRequestToOption(optionRequest);
            option.setOptionId(optionId);
            Option savedOption = optionRepository.save(option);
            return optionMapper.optionToOptionResponse(savedOption);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("option already exists");
        }
    }

    @Override
    public void deleteOption(Long optionId) {
        if(!optionRepository.findById(optionId).isPresent()){
            throw new NotFoundException("option not found");
        }
        try {
            optionRepository.deleteById(optionId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("option already used");
        }
    }

    @Override
    public List<OptionResponse> getAllOptions() {
        List<Option> options = optionRepository.findAll();
        List<OptionResponse> optionResponses = options.stream()
                .map(option -> optionMapper.optionToOptionResponse(option))
                .collect(Collectors.toList());
        return optionResponses;
    }
}
