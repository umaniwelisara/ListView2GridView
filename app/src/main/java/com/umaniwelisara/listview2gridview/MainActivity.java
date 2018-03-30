package com.umaniwelisara.listview2gridview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewStub stubGrid;
    private ViewStub stubList;
    public ListView listView;
    public GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridVieAdapter gridVieAdapter;
    private List<Product> productList;
    private int currentViewMode;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stubList=(ViewStub) findViewById(R.id.stub_list);
        stubGrid=(ViewStub)findViewById(R.id.stub_grid);

        //inflate viewstub before grid view

        stubList.inflate();
        stubGrid.inflate();

        listView=(ListView)findViewById(R.id.mylistview);
        gridView=(GridView)findViewById(R.id.mygridview);

        //get list of products
        getProductList();

        //get current view mode in shared reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode",MODE_PRIVATE);
        currentViewMode=sharedPreferences.getInt("currentViewMode",VIEW_MODE_LISTVIEW);//default is view listview

        //register item list
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) onItemClick);
        gridView.setOnItemClickListener((AdapterView.OnItemClickListener) onItemClick);



        switchView();
    }
    private void switchView(){
        if(VIEW_MODE_LISTVIEW == currentViewMode){
            //display list view
            stubList.setVisibility(View.VISIBLE);
            //hide grid view
            stubGrid.setVisibility(View.GONE);
            }
            else{
            //hidelist view
            stubList.setVisibility(View.GONE);
            //display grid view
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters(){

        if(VIEW_MODE_LISTVIEW ==currentViewMode){
            listViewAdapter = new ListViewAdapter(this,R.layout.list_item,productList);
            listView.setAdapter(listViewAdapter);
        }
        else {
            gridVieAdapter = new GridVieAdapter(this,R.layout.grid_item,productList);
            gridView.setAdapter(gridVieAdapter);
        }

    }

    public List<Product> getProductList(){

        //pseudo code to get product ,
        productList=new ArrayList<>();
        productList.add(new Product(R.drawable.flower1,"Title 1 ","this is description 1"));
        productList.add(new Product(R.drawable.flower2,"Title 2 ","this is description 2"));
        productList.add(new Product(R.drawable.flower3,"Title 3 ","this is description 3"));
        productList.add(new Product(R.drawable.flower4,"Title 4 ","this is description 4"));
        productList.add(new Product(R.drawable.flower5,"Title 5 ","this is description 5"));
        productList.add(new Product(R.drawable.flower6,"Title 6 ","this is description 6"));
        productList.add(new Product(R.drawable.flower1,"Title 7 ","this is description 7"));
        productList.add(new Product(R.drawable.flower2,"Title 8 ","this is description 8"));
        productList.add(new Product(R.drawable.flower3,"Title 9 ","this is description 9"));
        productList.add(new Product(R.drawable.flower4,"Title 10 ","this is description 10"));

        return productList;
    }
    AdapterView.OnItemClickListener onItemClick=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            switch (position){
                case 0:
                    Intent flower1Activity = new Intent(MainActivity.this,Flower1Activity.class);
                    startActivity(flower1Activity);
                    break;
                case 1:
                    Intent flower2Activity = new Intent(MainActivity.this,Flower2Activity.class);
                    startActivity(flower2Activity);
                    break;
                case 2:
                    Intent flower3Activity = new Intent(MainActivity.this,Flower3Activity.class);
                    startActivity(flower3Activity);
                    break;
                default:
                    break;
            }
        }
    };
/*
    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Toast.makeText(getApplicationContext(),productList.get(position).getTitle()+ " - " + productList.get(position).getDescription(),Toast.LENGTH_SHORT).show();
        }
    };
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_1:
                if(VIEW_MODE_LISTVIEW == currentViewMode){
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                }else{
                    currentViewMode=VIEW_MODE_LISTVIEW;
                }
                //switch view
                switchView();
                break;
        }
        return true;
    }
}
