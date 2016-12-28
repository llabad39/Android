package com.example.belynda.rssreader;

/**
 * Created by lucaslabadens on 26/12/2016.
 */

public class Card {
    private String question;
    private String answer;
    private int id;
    public Card(String q,String a,int i){
        question=q;
        answer=a;
        id=i;
    }
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
}
