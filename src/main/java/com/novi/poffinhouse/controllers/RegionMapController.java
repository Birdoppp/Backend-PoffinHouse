package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.RegionMapInputDto;
import com.novi.poffinhouse.dto.output.RegionMapOutputDto;
import com.novi.poffinhouse.exceptions.ResourceNotFoundException;
import com.novi.poffinhouse.services.RegionMapAtlasService;
import com.novi.poffinhouse.services.RegionMapService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/region-maps")
public class RegionMapController {
    private final RegionMapService regionMapService;
    private final RegionMapAtlasService regionMapAtlasService;

    public RegionMapController(RegionMapService regionMapService, RegionMapAtlasService regionMapAtlasService) {
        this.regionMapService = regionMapService;
        this.regionMapAtlasService = regionMapAtlasService;
    }

    @PostMapping
    public ResponseEntity<RegionMapOutputDto> createMap(@Valid @RequestBody RegionMapInputDto inputDto) {
        return new ResponseEntity<>(regionMapService.createRegionMap(inputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionMapOutputDto> getRegionMapById(@PathVariable Long id) {
        return ResponseEntity.ok(regionMapService.getRegionMapById(id));
    }

    @GetMapping
    public ResponseEntity<List<RegionMapOutputDto>> getAllRegionMaps() {
        return ResponseEntity.ok(regionMapService.getAllRegionMaps());
    }

    @GetMapping("/{id}/atlas")
    public ResponseEntity<Resource> getAtlas(@PathVariable("id") Long regionMapId, HttpServletRequest request) {
        try {
            RegionMapOutputDto outputDto = regionMapService.getRegionMapById(regionMapId);
            String fileName = outputDto.getRegionMapAtlas().getFileName();
            Resource resource = regionMapAtlasService.getImage(fileName);

            String mimeType;

            try {
                mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException e) {
                mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                    .body(resource);
        } catch (RuntimeException e) {
           throw new ResourceNotFoundException("This RegionMap currently does not have an Atlas image.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionMapOutputDto> updateMap(@PathVariable Long id,
                                                        @Valid @RequestBody RegionMapInputDto regionMap) {
        return ResponseEntity.ok(regionMapService.updateRegionMap(id, regionMap));
    }

    @PatchMapping("/{id}/atlas")
    public ResponseEntity<RegionMapOutputDto> addAtlas(@PathVariable("id") Long regionMapId,
                                                       @Valid @RequestBody MultipartFile atlas)
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/region-maps/")
                .path(Objects.requireNonNull(regionMapId.toString()))
                .path("/atlas")
                .toUriString();
        String fileName = regionMapAtlasService.addImage(atlas);
        RegionMapOutputDto regionMap = regionMapService.addAtlas(fileName, regionMapId);

        return ResponseEntity.created(URI.create(url)).body(regionMap);
    }
}
