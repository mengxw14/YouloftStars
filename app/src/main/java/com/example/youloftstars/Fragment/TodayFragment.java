package com.example.youloftstars.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.youloftstars.Adapter.FourtuneListAdapter;
import com.example.youloftstars.Adapter.FourtuneListOtherAdapter;
import com.example.youloftstars.Adapter.FraAdapter;
import com.example.youloftstars.Adapter.FrurtuneListNumberAdapter;
import com.example.youloftstars.R;
import com.example.youloftstars.bean.AstroldInfo;
import com.example.youloftstars.bean.AstroldMessage;
import com.example.youloftstars.bean.FragmentInfo;
import com.example.youloftstars.util.HttpUtils;
import com.example.youloftstars.util.RetrofitNetWork;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment {
    private String[] newTitles;
    private ArrayList<FragmentInfo> info;
    private FraAdapter mAdapter;
    private View view;
    private GridView mGridNumber;
    private GridView mGridOther;
    private ListView mListNumber;
    private View mView;
    private AstroldMessage mAstroldMessage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AstroldMessage message;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    FourtuneListAdapter adapter = new FourtuneListAdapter(getContext(),message);
//                    mGridNumber.setAdapter(adapter);
//                    FourtuneListOtherAdapter adapter1 = new FourtuneListOtherAdapter(getContext(),message);
//                    mGridOther.setAdapter(adapter1);
//                    FrurtuneListNumberAdapter adapter2 = new FrurtuneListNumberAdapter(getContext(),message);
//                    mListNumber.setAdapter(adapter2);
//                    break;
//            }
        }
    };
    public TodayFragment(AstroldMessage astroldMessage) {
        mAstroldMessage = astroldMessage;
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mView == null){
            mView = View.inflate(getContext(),R.layout.fragment_today,null);
            mGridNumber = mView.findViewById(R.id.grid_fourtune_number);
            mGridOther = mView.findViewById(R.id.grid_fourtune_other);
            mListNumber = mView.findViewById(R.id.list_fourtune_number);
            initData();
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //pager.setAdapter(mAdapter);
    }
//    private void initView(){
//        mGridNumber = getActivity().findViewById(R.id.grid_fourtune_number);
//        mGridOther = getActivity().findViewById(R.id.grid_fourtune_other);
//        mListNumber = getActivity().findViewById(R.id.list_fourtune_number);
//    }
    private void initData(){
        getViewInfo();
    }
    private void getViewInfo(){
        //通过网络请求来或许数据并且加入到对应的view中
        FourtuneListAdapter adapter = new FourtuneListAdapter(getContext(),mAstroldMessage);
        mGridNumber.setAdapter(adapter);
        if(mAstroldMessage.getNumber() == null){
            mGridOther.setVisibility(View.GONE);
        }else {
            FourtuneListOtherAdapter adapter1 = new FourtuneListOtherAdapter(getContext(),mAstroldMessage);
            mGridOther.setAdapter(adapter1);
        }
        FrurtuneListNumberAdapter adapter2 = new FrurtuneListNumberAdapter(getContext(),mAstroldMessage);
        mListNumber.setAdapter(adapter2);
//                    Message message1 = Message.obtain();
//                    message1.what = 0;
//                    handler.sendMessage(message1);
    }
}