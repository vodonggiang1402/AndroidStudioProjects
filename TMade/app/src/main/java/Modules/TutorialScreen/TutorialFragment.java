package Modules.TutorialScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;

import Helper.SharedPrefHelper;
import Modules.TutorialScreen.Tutorial.TutorialAdapter;
import Services.Symbol.SymbolModel;
import Services.Tutorial.TutorialModel;
import Services.Tutorial.TutorialResponse;

public class TutorialFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tutorial_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.setTitle(null);
        }

        final FragmentActivity c = getActivity();
        RecyclerView rcvTutorial = (RecyclerView) view.findViewById(R.id.rcv_tutorial);

        GridLayoutManager gridlayoutManager = new GridLayoutManager(this.getContext(), 2);
        rcvTutorial.setLayoutManager(gridlayoutManager);
        TutorialAdapter tutorialAdapter = new TutorialAdapter(this.getContext());

        tutorialAdapter.setData(getListTutorial(getContext()));
        rcvTutorial.setAdapter(tutorialAdapter);

        return view;
    }

    private ArrayList<TutorialModel> getListTutorial(Context context) {
        TutorialResponse data = (TutorialResponse) SharedPrefHelper.getSharedOBJECT(context,"tutorial_response", TutorialResponse.class);
        if (!data.list.isEmpty()) {
            return data.list;
        }
        return  null;
    }

}