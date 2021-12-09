package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class AddFavoriteSuccesEvent {
    public String favoriteName;
    public String favoriteType;
    public int id;
    public String qid;

    public AddFavoriteSuccesEvent() {

    }

    public AddFavoriteSuccesEvent(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public AddFavoriteSuccesEvent(String favoriteName, String favoriteType) {
        this.favoriteName = favoriteName;
        this.favoriteType = favoriteType;
    }

    public AddFavoriteSuccesEvent(String favoriteName, String favoriteType, int id) {
        this.favoriteName = favoriteName;
        this.favoriteType = favoriteType;
        this.id = id;
    }

    public AddFavoriteSuccesEvent(String favoriteName, String favoriteType, int id, String qid) {
        this.favoriteName = favoriteName;
        this.favoriteType = favoriteType;
        this.id = id;
        this.qid = qid;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public String getFavoriteType() {
        return favoriteType;
    }

    public void setFavoriteType(String favoriteType) {
        this.favoriteType = favoriteType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
