package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelAlbumPhotos.AlbumPhotos;
import com.example.vksbermvvm.data.modelAlbumPhotos.Size;
import com.example.vksbermvvm.domain.model.IProfileConverter;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертирует список фотографий из data сущностей в domain
 *
 * @author Цунский Роман on 2019-12-15
 */
public class AlbumPhotoListConverter implements IProfileConverter<AlbumPhotos, List<AlbumPhoto>> {

    private final List<AlbumPhoto> photosList = new ArrayList<>();

    @NonNull
    @Override
    public List<AlbumPhoto> convert(@NonNull AlbumPhotos albumPhotos) {
        photosList.clear();
        for (com.example.vksbermvvm.data.modelAlbumPhotos.Item responseAlbum : albumPhotos.response.items) {
            String sizeType = "";
            String url = "";
            for (Size size : responseAlbum.sizes) {
                if (size.type.equals("z")) {
                    sizeType = size.type;
                    url = size.url;
                } else {
                    sizeType = responseAlbum.sizes.get(0).type;
                    url = responseAlbum.sizes.get(0).url;
                }
            }
            photosList.add(new AlbumPhoto(responseAlbum.id,
                    responseAlbum.albumId,
                    sizeType,
                    url));
        }
        return photosList;
    }
}
