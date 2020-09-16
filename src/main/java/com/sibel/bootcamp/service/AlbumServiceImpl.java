package com.sibel.bootcamp.service;

import com.sibel.bootcamp.dao.AlbumDAO;
import com.sibel.bootcamp.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired AlbumDAO albumDao;
    @Override
    public int InsertAlbum(Album album) {
        return albumDao.InsertAlbum(album);
    }

    @Override
    public List<Album> GetAllAlbum() {
        return albumDao.GetAllAlbum();
    }

    @Override
    public Optional<Album> GetAlbum(int id) {
        return albumDao.GetAlbum(id);
    }

    @Override
    public void DeleteAlbum(int id) {
        albumDao.DeleteAlbum(id);

    }

    @Override
    public void UpdateAlbum(Album oldAlbum, Album album) {
        albumDao.UpdateAlbum(oldAlbum, album);

    }
}
