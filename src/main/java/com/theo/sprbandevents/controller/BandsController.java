package com.theo.sprbandevents.controller;

import com.theo.sprbandevents.model.Band;
import com.theo.sprbandevents.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bands")
public class BandsController {

    @Autowired
    private BandRepository bandRepository;

    @GetMapping
    public List<Band> listBands() {
        return bandRepository.findAll();
    }

    @PostMapping
    public Band registerBand(@RequestBody Band band) {
        return bandRepository.save(band);
    }

    @DeleteMapping("/{bandId}")
    public void deleteBand(@PathVariable Integer bandId){
        bandRepository.removeFromRelated(bandId);
        bandRepository.removeBand(bandId);
    }

    @PutMapping("/{bandId}")
    public void updateBand(@RequestBody Band band, @PathVariable Integer bandId) {
        band.setId(bandId);
        bandRepository.save(band);
    }
}
