package zw.co.vokers.zoledge.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.utils.PermissionsChecker;
import zw.co.vokers.zoledge.utils.VerticalStepperItemView;

/**
 * Created by VinceGee on 1/18/2018.
 */

public class NormalReprovisionFragment extends Fragment {
    private Context context;
    private View metalView;
    private Button callBtn;
    private RecyclerView mDataRecycler;
    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[5];
    private Button mNextBtn0, mNextBtn1, mPrevBtn1, mNextBtn2, mPrevBtn2, mNextBtn3, mPrevBtn3,mNextBtn4, mPrevBtn4;

    private int mActivatedColorRes = R.color.material_blue_500;
    private int mDoneIconRes = R.drawable.done;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.normal_reprov, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSteppers[0] = view.findViewById(R.id.stepper_0);
        mSteppers[1] = view.findViewById(R.id.stepper_1);
        mSteppers[2] = view.findViewById(R.id.stepper_2);
        mSteppers[3] = view.findViewById(R.id.stepper_3);
        mSteppers[4] = view.findViewById(R.id.stepper_4);

        VerticalStepperItemView.bindSteppers(mSteppers);

        mNextBtn0 = view.findViewById(R.id.button_next_0);
        mNextBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[0].nextStep();
            }
        });

        mPrevBtn1 = view.findViewById(R.id.button_prev_1);
        mPrevBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[1].prevStep();
            }
        });

        mNextBtn1 = view.findViewById(R.id.button_next_1);
        mNextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[1].nextStep();
            }
        });

        mPrevBtn2 = view.findViewById(R.id.button_prev_2);
        mPrevBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[2].prevStep();
            }
        });

        mNextBtn2 = view.findViewById(R.id.button_next_2);
        mNextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[2].nextStep();
            }
        });

        ///////////
        mPrevBtn3 = view.findViewById(R.id.button_prev_3);
        mPrevBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[3].prevStep();
            }
        });

        mNextBtn3 = view.findViewById(R.id.button_next_3);
        mNextBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[3].nextStep();
            }
        });

        mPrevBtn4 = view.findViewById(R.id.button_prev_4);
        mPrevBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSteppers[4].prevStep();
            }
        });

        mNextBtn4 = view.findViewById(R.id.button_next_4);
        mNextBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mSteppers[1].nextStep();
                Snackbar.make(view, "Client should be connected now.", Snackbar.LENGTH_LONG).show();
            }
        });
/////////

    }


}
