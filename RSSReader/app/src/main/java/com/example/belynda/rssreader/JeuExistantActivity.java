package com.example.belynda.rssreader;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JeuExistantActivity extends AppCompatActivity {
    TextView res;
    LinearLayout lilPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_existant);

        // Tout d'abord, il faut détruire tous les éléments contenus dans le layout.
        LinearLayout layoutOfDynamicContent = (LinearLayout) findViewById(R.id.globale);
        layoutOfDynamicContent.removeAllViewsInLayout();

        // Ensuite il faut définir et instancier la ScrollView.
        // Le this correspond à l'Activité contenant ces lignes.
        ScrollView scvMain = new ScrollView(this);

        // On définit la couleur de fond du scroll.
        // scvMain .setBackgroundColor(0xFFCFDBEC);


        // Et on rajoute la ScrollView au layout principal en lui demandant de remplir tout l'espace disponible.
        layoutOfDynamicContent.addView(scvMain, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        // Ensuite on définit le layout qui contiendra les composants ajoutés dynamiquement.
        lilPost = new LinearLayout(this);

        // Définition de son orientation.
        lilPost.setOrientation(LinearLayout.VERTICAL);

        //Définition de son padding (le padding est la distance en pixels, entre le bord du composant et son contenu).
        lilPost.setPadding(0, 5, 0, 0);

        //Définition de la couleur de fond du layout.
        // lilPost .setBackgroundColor(0xFFFFFFFF);


        // Paramètres spécifiques au placement du composant webPicture au sein du layout :

        // Définition du paramètre de placement du composant au sein du layout et instanciation (avec la spécification de la manière dont le composant va remplir l'espace).
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Définition de la gravité du composant (comment il se positionne au sein de la zone qui lui est allouée).
        params.gravity = Gravity.CENTER_VERTICAL;



        // Définition des marges du composant (distance, en pixels, autour du composant).
        params.setMargins(5, 5, 5, 5);

        // Ajout du composant dans le layout avec les paramètres de positionnement définis ci-dessus.
        //lilPost .addView( webPicture , params );

        // Ajout du layout à la ScrollView en lui demandant de remplir tout l'espace disponible.
        scvMain.addView(lilPost, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));


        for (int i = 0; i < 4; i++) {
            // Tout d'abord, déclarez et instanciez le composant en lui passant son Contexte.
            // Ici le contexte n'est autre que l'activité dans lequel l'IHM est construite, d'où le this.
            final Button b1 = new Button(this);

            // Affectation du texte à afficher par le composant.
            //ici recuperer nom du jeu
            b1.setText("Apprendre l'anglais ");
            b1.setId(i+1);
            b1.setOnClickListener(btnclick);

            // Affectation de sa couleur de fond.
            //  b1.setBackgroundColor(0xCCCCCC);

            // Affectation de sa couleur de text.
            //  b1.setTextColor(0xFFFFFFFF);

            // Le texte à afficher quand il n'y a aucun texte à afficher par
            // le composant (i.e. quand le composant n'a rien à afficher).
            b1.setHint("Aucun jeu");
            // La couleur du hint-texte.
            b1.setHintTextColor(0xFF555555);

            // Pour permettre de sauver en mémoire l'état du composant
            // quand l'activité sera détruite pour être restauré plus tard.
            b1.setFreezesText(true);

            // Ajout d'une scroll bar au composant.
            b1.setHorizontallyScrolling(true);

            // Mise en place d'une ligne de transparence horizontale (à droite et à gauche)
            // lors du scroll du composant ou setVerticalFadingEdgeEnabled pour
            // mettre cette ligne verticale (en haut et en bas), ou les deux.
            // Si le composant n'a pas de scrollBar, cela ne fera rien.
            b1.setHorizontalFadingEdgeEnabled(true);

            // Définition de la hauteur/largeur de cette ligne de transparence.
            b1.setLines(2);

            // Gérez la visibilité du composant,
            // il peut être Visible, Invisible (caché mais l'espace pour le composant reste réservé)
            // ou Gone (caché et l'espace du composant est libéré et utilisé par les autres composants).
            b1.setVisibility(View.VISIBLE);

            // Et ajout du composant au layout qui le contient.
            lilPost.addView(b1, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
        }

    }
    View.OnClickListener btnclick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case 1:
                    Intent intent = new Intent(getApplicationContext(),
                            Jouer.class);
                    intent.putExtra(DetailActivity.EXTRA_URL,"Non du jeu choisis");
                    startActivity(intent);
                    //first button click
                    break;
                //Second button click
                case 2:
                    Intent intent2 = new Intent(getApplicationContext(),
                            Jouer.class);
                    intent2.putExtra(DetailActivity.EXTRA_URL,"Non du jeu choisis");
                    startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent(getApplicationContext(),
                            Jouer.class);
                    intent3.putExtra(DetailActivity.EXTRA_URL,"Non du jeu choisis");
                    startActivity(intent3);
                    //third button click
                    break;
                case 4:
                    Intent intent4 = new Intent(getApplicationContext(),
                            Jouer.class);
                    intent4.putExtra(DetailActivity.EXTRA_URL,"Non du jeu choisis");
                    startActivity(intent4);
                    //fourth button click
                    break;
                default:
                    break;
            }
        }
    };
}

