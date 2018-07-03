package com.example.c_auevangelista.sample.models;

import com.orm.SugarRecord;



public class GitHubModel extends SugarRecord<GitHubModel>{

    public String thumbnailUrl;
    public String albumId;
    public String _id;
    public String title;
    public String url;

    @Override
    public String toString() {
        return "GitHubModel{" +
                "thumbnailUrl='" + thumbnailUrl + '\'' +
                ", albumId='" + albumId + '\'' +
                ", _id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
//
//    public GitHubModel(){
//
//    }
//
//    public GitHubModel(String thumbnailUrl, String albumId, String _id, String title, String url) {
//        this.thumbnailUrl = thumbnailUrl;
//        this.albumId = albumId;
//        this._id = _id;
//        this.title = title;
//        this.url = url;
//    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
