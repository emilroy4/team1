package com.example.team1.ui.gallery;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

        import com.example.team1.R;

        import java.util.ArrayList;
        import java.util.List;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<List<Integer>> imageResources;

    public GalleryViewModel() {
        imageResources = new MutableLiveData<>();
        // Add your image resources to the list (replace R.drawable.image1, R.drawable.image2, etc. with your actual drawable resources)
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.zidane);
        images.add(R.drawable.henry);
        images.add(R.drawable.vieria);
        imageResources.setValue(images);
    }

    public LiveData<List<Integer>> getImageResources() {
        return imageResources;
    }
}

