package com.example.dogapp.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp.R;
import com.example.dogapp.databinding.FragmentDetailBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

public class detailFragment extends Fragment {
    private DogBreed dogBreed;
    private FragmentDetailBinding binding;
    private ImageView imageDog;
    private TextView tvName;
    private TextView tvLifeSpan;
    private TextView tvOrigin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dogBreed = (DogBreed) getArguments().getSerializable("dogBreed");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_detail, null, false);
        View viewRoot = binding.getRoot();

        tvName = (TextView) viewRoot.findViewById(R.id.tv_namedog_detail);
        tvOrigin = (TextView) viewRoot.findViewById(R.id.tv_origin_detail);
        tvLifeSpan = (TextView) viewRoot.findViewById(R.id.tv_lifespan_detail);
        imageDog = (ImageView) viewRoot.findViewById(R.id.image_dog);
        Picasso.get().load(dogBreed.getUrl()).into(imageDog);

        binding.setDog(dogBreed);
        return viewRoot;
    }
}