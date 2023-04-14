package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.OptionRequest;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<Object> saveOption(@Valid @RequestBody OptionRequest optionRequest){
        return ResponseHandler.responseBuilder("Option created", HttpStatus.CREATED,
                optionService.saveOption(optionRequest));
    }
    @PutMapping("/{optionId}")
    public ResponseEntity<Object> updateOption(@Valid @RequestBody OptionRequest optionRequest,
                                               @PathVariable Long optionId){
        return ResponseHandler.responseBuilder("Option updated", HttpStatus.OK,
                optionService.updateOption(optionRequest,optionId));
    }
    @DeleteMapping("/{optionId}")
    public void deleteOption(@PathVariable Long optionId){
        optionService.deleteOption(optionId);
    }
    @GetMapping
    public ResponseEntity<Object> getAllOptions(){
        return ResponseEntity.ok().body(optionService.getAllOptions());
    }
}
