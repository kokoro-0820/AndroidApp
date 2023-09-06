package jp.ac.meijou.android.s221205056;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.Optional;

import jp.ac.meijou.android.s221205056.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s221205056.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()){
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: "+ text)
                                .ifPresent(text -> binding.textView.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.textView.setText("Result: Canceled");
                        break;
                    default:
                        binding.textView.setText("Result: Unknown("+ result.getResultCode() + ")");
                        break;
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(v -> {
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });

        binding.button2.setOnClickListener(v -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp "));
            startActivity(intent);
        });

        binding.button3.setOnClickListener(v -> {
            var intent = new Intent(this, SubActivity.class);
            intent.putExtra("text", binding.editText.getText().toString());
            startActivity(intent);
        });

        binding.button4.setOnClickListener(v -> {
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });
    }
}