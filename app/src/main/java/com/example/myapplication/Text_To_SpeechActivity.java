package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class Text_To_SpeechActivity extends AppCompatActivity {

    EditText et_enter_text;
    SeekBar sb_pitch, sb_speed;
    Button btn_speak;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        setTitle("Text To Speech");

        et_enter_text = findViewById(R.id.et_text_speech_enter_your_text);
        sb_pitch  = findViewById(R.id.sb_text_speech_pitch);
        sb_speed = findViewById(R.id.sb_text_speech_speed);
        btn_speak = findViewById(R.id.btn_text_speech_speak);

        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakText();
            }
        });

        textToSpeech = new TextToSpeech(Text_To_SpeechActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = textToSpeech.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(Text_To_SpeechActivity.this, "This Language is not Supportive", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Text_To_SpeechActivity.this, "Failed To Initalization", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void speakText() {
        String text = et_enter_text.getText().toString();

        if (text.isEmpty() ||"".equals(text))
        {
            text = "Please Enter Some Text To Speak";
        }

        float pitch = (float) sb_pitch.getProgress()/50;
        if (pitch <0.1)
        {
            pitch = 0.1f;
        }
        textToSpeech.setPitch(pitch);

        float speed = (float) sb_speed.getProgress()/50;
        if (speed < 0.1)
        {
            speed = 0.1f;
        }
        textToSpeech.setSpeechRate(speed);

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);

    }
}