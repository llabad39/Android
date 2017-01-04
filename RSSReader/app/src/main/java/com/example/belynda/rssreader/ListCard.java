package com.example.belynda.rssreader;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lucaslabadens on 26/12/2016.
 */

public class ListCard extends ArrayList<Card> implements Parcelable{
    ListCard(){
    }
    protected ListCard(Parcel in) {
        this.getFromParcel(in);
    }

    public void getFromParcel(Parcel in)
    {
        this.clear();
        int size = in.readInt();
        for(int i = 0; i < size; i++)
        {
            Card card = in.readParcelable(Card.class.getClassLoader());
            this.add(card);
        }

    }
    public static final Creator<ListCard> CREATOR = new Creator<ListCard>() {
        @Override
        public ListCard createFromParcel(Parcel in) {
            return new ListCard(in);
        }

        @Override
        public ListCard[] newArray(int size) {
            return new ListCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.size());
        for(int i=0;i<this.size();i++){
            Card  card = this.get(i);
            dest.writeParcelable(card,flags);
        }
    }

}
