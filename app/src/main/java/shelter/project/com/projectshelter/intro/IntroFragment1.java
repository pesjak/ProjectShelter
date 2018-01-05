package shelter.project.com.projectshelter.intro;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shelter.project.com.projectshelter.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment1 extends Fragment {


    @BindView(R.id.ivImage)
    ImageView ivImage;
    Unbinder unbinder;


    public static IntroFragment1 newInstance() {
        return new IntroFragment1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro1, container, false);
        unbinder = ButterKnife.bind(this, view);
        Glide.with(view).load(R.drawable.intro1).into(ivImage);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
