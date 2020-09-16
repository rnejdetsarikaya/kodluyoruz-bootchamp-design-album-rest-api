package com.sibel.bootcamp.service;

import com.sibel.bootcamp.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    public int InsertAlbum(Album album);
    public List<Album> GetAllAlbum();
    public Optional<Album> GetAlbum(int id);
    public void DeleteAlbum(int id);
    public void UpdateAlbum(Album oldAlbum, Album album);
}
