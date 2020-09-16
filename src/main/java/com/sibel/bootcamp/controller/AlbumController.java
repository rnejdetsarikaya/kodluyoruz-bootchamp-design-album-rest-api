package com.sibel.bootcamp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sibel.bootcamp.exceptions.AlbumDeleteException;
import com.sibel.bootcamp.exceptions.AlbumDuplicateException;
import com.sibel.bootcamp.exceptions.AlbumNotFoundException;
import com.sibel.bootcamp.model.Album;
import com.sibel.bootcamp.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/v1/albums")
@RestController
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping()
    public List<Album> getAllAlbum() {
        System.out.println("All Album");
        return albumService.GetAllAlbum();
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable int id) throws AlbumNotFoundException {
        System.out.println("Album by id");
        return albumService.GetAlbum(id).orElseThrow(() -> new AlbumNotFoundException());
    }

    @PostMapping
    public ResponseEntity insertAlbum(@RequestBody Album album) {
        int id = albumService.InsertAlbum(album);
        if (id == -1)
            throw new AlbumDuplicateException();
        URI location = URI.create(String.format("/v1/albums/%s", id));
        System.out.println("Created Album");
        return ResponseEntity.created(location).build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAlbum(@PathVariable int id, @RequestBody JsonPatch patch) {
        try {
            Album album = albumService.GetAlbum(id).orElseThrow(() -> new AlbumNotFoundException());
            Album albumPatched = applyPatchToAlbum(patch, album);
            albumService.UpdateAlbum(album, albumPatched);
            System.out.println("Updated Album");
            return ResponseEntity.noContent().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (AlbumNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlbum(@PathVariable int id) {
        try {
            albumService.DeleteAlbum(id);
            System.out.println("Deleted Album");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new AlbumDeleteException();
        }
    }

    private Album applyPatchToAlbum(JsonPatch patch, Album targetAlbum) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetAlbum, JsonNode.class));
        return objectMapper.treeToValue(patched, Album.class);
    }
}
