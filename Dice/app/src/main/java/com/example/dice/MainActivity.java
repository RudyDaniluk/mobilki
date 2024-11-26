package com.example.dice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView textWynikGry, textWynikRundy;
    private ImageView im1,im2,im3,im4,im5;
    private Button buttonGraj, buttonReset;
    private int punktyRunda=0;
    private int punktyGra=0;
    int[] tab = new int[6];
    int ileRaz=0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGraj = findViewById(R.id.button);
        buttonReset=findViewById(R.id.button2);
        im1=findViewById(R.id.imageView);
        im2=findViewById(R.id.imageView2);
        im3=findViewById(R.id.imageView3);
        im4=findViewById(R.id.imageView4);
        im5=findViewById(R.id.imageView5);
        textWynikGry=findViewById(R.id.textView2);
        textWynikRundy=findViewById(R.id.textView3);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWynikGry.setText("Wynik gry: ");
                textWynikRundy.setText("Wynik tego losowania:");
                im1.setImageResource(R.drawable.kostkapusta);
                im2.setImageResource(R.drawable.kostkapusta);
                im3.setImageResource(R.drawable.kostkapusta);
                im4.setImageResource(R.drawable.kostkapusta);
                im5.setImageResource(R.drawable.kostkapusta);

            }
        });
        buttonGraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<5;i++) {
                    tab[i]=random.nextInt(6)+1;
                }
                punktyRunda=0;
                int punktyRunda = 0;
                Set<Integer> sprawdzoneLiczby = new HashSet<>();

                // Przeszukiwanie tablicy w celu zliczenia punktów
                for (int i = 0; i < 5; i++) {
                    if (!sprawdzoneLiczby.contains(tab[i])) {
                        int ileRaz = 1;

                        for (int j = i + 1; j < 5; j++) {
                            if (tab[i] == tab[j]) {
                                ileRaz++;
                            }
                        }

                        System.out.println("Znaleziono " + tab[i] + " tyle razy " + ileRaz);

                        if (ileRaz > 1) {
                            punktyRunda += ileRaz * tab[i];
                        }

                        sprawdzoneLiczby.add(tab[i]);
                    }
                }
                punktyGra = punktyGra + punktyRunda;
                textWynikGry.setText("Wynik gry: " + punktyGra);
                textWynikRundy.setText("Wynik tego losowania:"+punktyRunda);
                switch (tab[0]) {
                    case 1:
                        im1.setImageResource(R.drawable.kostka1);
                        break;
                    case 2:
                        im1.setImageResource(R.drawable.kostka2);
                        break;
                    case 3:
                        im1.setImageResource(R.drawable.kostka3);
                        break;
                    case 4:
                        im1.setImageResource(R.drawable.kostka4);
                        break;
                    case 5:
                        im1.setImageResource(R.drawable.kostka5);
                        break;
                    case 6:
                        im1.setImageResource(R.drawable.kostka6);
                        break;
                }
                switch (tab[1]) {
                    case 1:
                        im2.setImageResource(R.drawable.kostka1);
                        break;
                    case 2:
                        im2.setImageResource(R.drawable.kostka2);
                        break;
                    case 3:
                        im2.setImageResource(R.drawable.kostka3);
                        break;
                    case 4:
                        im2.setImageResource(R.drawable.kostka4);
                        break;
                    case 5:
                        im2.setImageResource(R.drawable.kostka5);
                        break;
                    case 6:
                        im2.setImageResource(R.drawable.kostka6);
                        break;
                }
                switch (tab[2]) {
                    case 1:
                        im3.setImageResource(R.drawable.kostka1);
                        break;
                    case 2:
                        im3.setImageResource(R.drawable.kostka2);
                        break;
                    case 3:
                        im3.setImageResource(R.drawable.kostka3);
                        break;
                    case 4:
                        im3.setImageResource(R.drawable.kostka4);
                        break;
                    case 5:
                        im3.setImageResource(R.drawable.kostka5);
                        break;
                    case 6:
                        im3.setImageResource(R.drawable.kostka6);
                        break;
                }
                switch (tab[3]) {
                    case 1:
                        im4.setImageResource(R.drawable.kostka1);
                        break;
                    case 2:
                        im4.setImageResource(R.drawable.kostka2);
                        break;
                    case 3:
                        im4.setImageResource(R.drawable.kostka3);
                        break;
                    case 4:
                        im4.setImageResource(R.drawable.kostka4);
                        break;
                    case 5:
                        im4.setImageResource(R.drawable.kostka5);
                        break;
                    case 6:
                        im4.setImageResource(R.drawable.kostka6);
                        break;
                }
                switch (tab[4]) {
                    case 1:
                        im5.setImageResource(R.drawable.kostka1);
                        break;
                    case 2:
                        im5.setImageResource(R.drawable.kostka2);
                        break;
                    case 3:
                        im5.setImageResource(R.drawable.kostka3);
                        break;
                    case 4:
                        im5.setImageResource(R.drawable.kostka4);
                        break;
                    case 5:
                        im5.setImageResource(R.drawable.kostka5);
                        break;
                    case 6:
                        im5.setImageResource(R.drawable.kostka6);
                        break;
                }

            }



        });
    }


}