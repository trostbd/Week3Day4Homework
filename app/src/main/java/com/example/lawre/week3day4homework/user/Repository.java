package com.example.lawre.week3day4homework.user;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    public final static Parcelable.Creator<Repository> CREATOR = new Creator<Repository>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return (new Repository[size]);
        }

    };

    protected Repository(Parcel in) {
        in.readList(this.items, (com.example.lawre.week3day4homework.user.Item.class.getClassLoader()));
    }

    public Repository() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public int describeContents() {
        return 0;
    }
}
