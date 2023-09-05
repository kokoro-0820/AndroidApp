package jp.ac.meijou.android.s221205056;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.ImageView;

import jp.ac.meijou.android.s221205056.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.text.setText(R.string.my_name);

        binding.button.setOnClickListener(v -> {
            var text = binding.editText.getText().toString();
            binding.text.setText(text);

        });

        binding.button2.setOnClickListener(v -> {
            binding.text.setText(R.string.my_name);

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


    }
}