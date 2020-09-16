package com.sibel.bootcamp.dao;

import com.sibel.bootcamp.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumDAO {
    public int InsertAlbum(Album album);
    public List<Album> GetAllAlbum();
    public Optional<Album> GetAlbum(int id);
    public void DeleteAlbum(int id);
    public void UpdateAlbum(Album oldAlbum, Album album);
}
