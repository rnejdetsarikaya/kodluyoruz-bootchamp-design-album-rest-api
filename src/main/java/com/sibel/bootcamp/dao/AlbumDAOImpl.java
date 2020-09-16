package com.sibel.bootcamp.dao;


import com.sibel.bootcamp.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class AlbumDAOImpl implements AlbumDAO{
    List<Album> albumList = new ArrayList<Album>();
    private Object AlbumNotFoundException;

    @Override
    public int InsertAlbum(Album album) {
        Random rand = new Random();
        int id = rand.nextInt(10);
        System.out.println(id);
        try{
            Album check = albumList.stream()
                    .filter(a -> a.getId()==id)
                    .findFirst().get();
            return -1;
        }catch (Exception e){
            album.setId(id);
            albumList.add(album);
            return id;
        }

    }

    @Override
    public List<Album> GetAllAlbum() {
        return albumList;
    }

    @Override
    public Optional<Album> GetAlbum(int id) {
        return albumList.stream().filter(album ->  id==album.getId()).findFirst();
    }

    @Override
    public void DeleteAlbum(int id) {
        Album album = GetAlbum(id).get();
        albumList.remove(album);
    }

    @Override
    public void UpdateAlbum(Album oldAlbum, Album album) {
        albumList.set(albumList.indexOf(oldAlbum),album);
    }


}
