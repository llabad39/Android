package com.example.belynda.rssreader;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lucaslabadens on 26/12/2016.
 */

public class Card implements Parcelable{
    private String question;
    private String answer;
    private int id;
    public Card(String q,String a,int i){
        question=q;
        answer=a;
        id=i;
    }

    public Card(Parcel in){
        this.getFromParcel(in);
    }

    public void getFromParcel(Parcel in)
    {
        this.question = in.readString();
        this.answer  = in.readString();
        this.id = in.readInt();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public Card createFromParcel(Parcel in)
        {
            return new Card(in);
        }

        @Override
        public Object[] newArray(int size) {
            return null;
        }
    };

    String getQuestion(){
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }
    public void affiche(){System.out.println("question "+question);System.out.println("reponse :"+answer);System.out.println("id :"+id);}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeInt(id);
    }
}
