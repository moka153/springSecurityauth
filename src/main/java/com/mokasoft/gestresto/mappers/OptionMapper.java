package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.OptionRequest;
import com.mokasoft.gestresto.dtos.OptionResponse;
import com.mokasoft.gestresto.entities.Option;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {
    public Option optionRequestToOption(OptionRequest optionRequest){
        Option option = Option.builder()
                .optionId(optionRequest.getOptionId())
                .name(optionRequest.getName())
                .build();
        return option;
    }
    public OptionResponse optionToOptionResponse(Option option){
        OptionResponse optionResponse = OptionResponse.builder()
                .optionId(option.getOptionId())
                .name(option.getName())
                .build();
        return optionResponse;
    }
}
