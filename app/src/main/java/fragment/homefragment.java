package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.XDev.Sandesh.MyAdapter.MyAdapter;
import com.XDev.Sandesh.R;

import java.util.ArrayList;


public class homefragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<datamodel> dataholder;

    public homefragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_homefragment, container, false);
//        recyclerView=view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        dataholder=new ArrayList<>();

//        datamodel ob1=new datamodel(R.drawable.angular,"Angular","Web Application");
//        dataholder.add(ob1);
//
//        datamodel ob2=new datamodel(R.drawable.cp,"C Programming","Embed Programming");
//        dataholder.add(ob2);
//
//        datamodel ob3=new datamodel(R.drawable.cpp,"C++ Programming","Embed Programming");
//        dataholder.add(ob3);
//
//        datamodel ob4=new datamodel(R.drawable.dotnet,".NET Programming","Desktop and Web Programming");
//        dataholder.add(ob4);
//
//        datamodel ob5=new datamodel(R.drawable.java,"Java Programming","Desktop and Web Programming");
//        dataholder.add(ob5);
//
//        datamodel ob6=new datamodel(R.drawable.magento,"Magento","Web Application Framework");
//        dataholder.add(ob6);
//
//        datamodel ob7=new datamodel(R.drawable.nodejs,"NodeJS","Web Application Framework");
//        dataholder.add(ob7);
//
//        datamodel ob8=new datamodel(R.drawable.python,"Python","Desktop and Web Programming");
//        dataholder.add(ob8);
//
//        datamodel ob9=new datamodel(R.drawable.shopify,"Shopify","E-Commerce Framework");
//        dataholder.add(ob9);
//
//        datamodel ob10=new datamodel(R.drawable.wordpress,"Wordpress","WebApplication Framewrok");
//        dataholder.add(ob10);

//        recyclerView.setAdapter(new MyAdapter(dataholder));

        return view;
    }
}