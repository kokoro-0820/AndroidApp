package jp.ac.meijou.android.s221205056;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;

import jp.ac.meijou.android.s221205056.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);


        binding.button.setOnClickListener(v -> {
            var text = binding.editText.getText().toString();
            binding.text.setText(text);

        });

        binding.button2.setOnClickListener(v -> {
            var text = binding.editText.getText().toString();
            prefDataStore.setString("name", text);

            Log.d("kokoro", "save text:"+ binding.text.getText());

        });

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // テキストが更新される直前に呼ばれる
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 文字を1つ入力された時に呼ばれる
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // テキストが更新されたあとに呼ばれる
                binding.text.setText(editable.toString());
            }

        });

        Log.d("kokoro", "onCreate text:"+ binding.text.getText());
    }

    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));

        Log.d("kokoro", "onStart text:"+ binding.text.getText());
    }
}