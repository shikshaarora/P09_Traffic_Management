package cultoftheunicorn.marvel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import org.opencv.cultoftheunicorn.marvel.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class SearchBylplate extends AppCompatActivity {
    //private static final String BASE_URL = "http://10.0.2.2";
    private static final String BASE_URL = "https://criminaldetect.000webhostapp.com";
    private static final String FULL_URL = BASE_URL+"/test/";
    class Spacecraft {
        @SerializedName("id")
        private int id;
        @SerializedName("lno")
        private String lno;
        @SerializedName("Comments")
        private String Comments;
        @SerializedName("Violation")
        private String Violation;
        @SerializedName("image_url")
        private String imageURL;
        @SerializedName("technology_exists")
        private int technologyExists;
        @SerializedName("count")
        private String countt;
        @SerializedName("place")
        private String place;
        @SerializedName("Lplate")
        private String Lplate;
        @SerializedName("date")
        private String date;
        @SerializedName("amount")
        private String amount;
        @SerializedName("name")
        private String name;


        public Spacecraft(int id, String lno, String Comments,String Violation, String imageURL, int technologyExists, String countt, String place, String Lplate, String date, String amount, String name) {
            this.id = id;
            this.lno = lno;
            this.Comments = Comments;
            this.Violation=Violation;
            this.imageURL = imageURL;
            this.technologyExists = technologyExists;
            this.countt = countt;
            this.place = place;
            this.Lplate = Lplate;
            this.date = date;
            this.amount = amount;
            this.name = name;

        }

        /*
         *GETTERS AND SETTERS
         */
        public int getid() {
            return id;
        }
        public void setid(int id) {
            this.id = id;
        }
        public String getlno() {
            return lno;
        }
        public String getComments() {
            return Comments;
        }
        public String getViolation() {
            return Violation;
        }
        public String getImageURL() {
            return imageURL;
        }
        public int getTechnologyExists() {
            return technologyExists;
        }
        public String getcoount() {
            return countt;
        }
        public String getplace() {
            return place;
        }
        public String getlplate() {
            return Lplate;
        } public String getdate() {
            return date;
        }
        public String getamount() {
            return amount;
        } public String getname() {
            return name;
        }
        @Override
        public String toString() {
            return lno;
        }
    }

    interface MyAPIService {

        @GET("/test")
        Call<List<Spacecraft>> getSpacecrafts();
    }

    static class RetrofitClientInstance {
        private static Retrofit retrofit;

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

    class FilterHelper extends Filter {
        private List<Spacecraft> currentList;
        private ListViewAdapter adapter;
        private Context c;

        public FilterHelper(List<Spacecraft> currentList, ListViewAdapter adapter, Context c) {
            this.currentList = currentList;
            this.adapter = adapter;
            this.c=c;
        }
        /*
        - Perform actual filtering.
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults=new FilterResults();

            if(constraint != null && constraint.length()>0)
            {
                //CHANGE TO UPPER
                constraint=constraint.toString().toUpperCase();

                //HOLD FILTERS WE FIND
                ArrayList<Spacecraft> foundFilters=new ArrayList<>();

                Spacecraft spacecraft=null;

                //ITERATE CURRENT LIST
                for (int i=0;i<currentList.size();i++)
                {
                    spacecraft= currentList.get(i);

                    //SEARCH
                    if(spacecraft.getlplate().toUpperCase().contains(constraint) )
                    {
                        //ADD IF FOUND
                        foundFilters.add(spacecraft);
                    }
                }

                //SET RESULTS TO FILTER LIST
                filterResults.count=foundFilters.size();
                filterResults.values=foundFilters;
            }else
            {
                //NO ITEM FOUND.LIST REMAINS INTACT
                filterResults.count=currentList.size();
                filterResults.values=currentList;
            }

            //RETURN RESULTS
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            adapter.setSpacecrafts((ArrayList<Spacecraft>) filterResults.values);
            adapter.refresh();
        }
    }

    class ListViewAdapter extends BaseAdapter implements Filterable {

        private List<Spacecraft> spacecrafts;
        private Context context;
        private List<Spacecraft> currentList;
        private FilterHelper filterHelper;

        public ListViewAdapter(Context context,List<Spacecraft> spacecrafts){
            this.context = context;
            this.spacecrafts = spacecrafts;
            this.currentList=spacecrafts;
        }

        @Override
        public int getCount() {
            return spacecrafts.size();
        }

        @Override
        public Object getItem(int pos) {
            return spacecrafts.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view=LayoutInflater.from(context).inflate(R.layout.modelforlplate,viewGroup,false);
            }

            TextView LnoTxt = (TextView) view.findViewById(R.id.LplateTextView);
            TextView lplate = (TextView) view.findViewById(R.id.lplateeTextView);
            TextView place = (TextView) view.findViewById(R.id.placeTextView);
            TextView records = (TextView) view.findViewById(R.id.recordsTextView);

            ImageView spacecraftImageView = (ImageView) view.findViewById(R.id.spacecraftImageView);

            final Spacecraft thisSpacecraft= spacecrafts.get(position);

            LnoTxt.setText(thisSpacecraft.getlno());
            lplate.setText(thisSpacecraft.getlplate());
            place.setText(thisSpacecraft.getplace());
            records.setText(thisSpacecraft.getcoount());

            if(thisSpacecraft.getImageURL() != null && thisSpacecraft.getImageURL().length()>0)
            {
                Picasso.get().load(FULL_URL+"/images/"+thisSpacecraft.getImageURL()).placeholder(R.drawable.placeholder).into(spacecraftImageView);
            }else {
                Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.placeholder).into(spacecraftImageView);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, thisSpacecraft.getlno(), Toast.LENGTH_SHORT).show();
                    String techExists="";
                    if(thisSpacecraft.getTechnologyExists()==1){
                        techExists="YES";
                    }else{
                        techExists="NO";
                    }

                    String[] spacecrafts = {
                            thisSpacecraft.getlno(),
                            thisSpacecraft.getComments(),
                            thisSpacecraft.getViolation(),
                            thisSpacecraft.getcoount(),
                            techExists,
                            FULL_URL+"/images/"+thisSpacecraft.getImageURL(),
                            thisSpacecraft.getplace(),
                            thisSpacecraft.getlplate(), thisSpacecraft.getdate(),
                            thisSpacecraft.getamount(), thisSpacecraft.getname()

                    };
                    openDetailActivity(spacecrafts);


                }
            });


            return view;
        }
        private void openDetailActivity(String[] data) {
            Intent intent = new Intent(SearchBylplate.this, DetailsActivity.class);
            intent.putExtra("lno_KEY", data[0]);
            intent.putExtra("Comments_KEY", data[1]);
            intent.putExtra("Violation_KEY", data[2]);
            intent.putExtra("TECHNOLOGY_EXISTS_KEY", data[3]);
            intent.putExtra("IMAGE_KEY", data[5]);
            intent.putExtra("count_KEY", data[3]);
            intent.putExtra("place_KEY", data[6]);
            intent.putExtra("Lplate_KEY", data[7]);
            intent.putExtra("date_KEY", data[8]);
            intent.putExtra("amount_KEY", data[9]);
            intent.putExtra("name_KEY", data[10]);
            startActivity(intent);
        }


        public void setSpacecrafts(ArrayList<Spacecraft> filteredSpacecrafts)
        {
            this.spacecrafts=filteredSpacecrafts;
        }
        @Override
        public Filter getFilter() {
            if(filterHelper==null)
            {
                filterHelper=new FilterHelper(currentList,this,context);
            }
            return filterHelper;
        }
        public void refresh(){
            notifyDataSetChanged();
        }
    }

    private ListViewAdapter adapter;
    private ListView mListView;
    private ProgressBar mProgressBar;
    private SearchView mSearchView;

    private void initializeWidgets(){
        mListView = (ListView) findViewById(R.id.mListView);
        mProgressBar= (ProgressBar) findViewById(R.id.mProgressBar);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
        mSearchView= (SearchView) findViewById(R.id.mSearchView);
        mSearchView.setIconified(true);
        //  mSearchView.setQuery(getIntent().getStringExtra("Search"));
    }

    private void populateListView(List<Spacecraft> spacecraftList) {
        adapter = new ListViewAdapter(this,spacecraftList);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_lplate);

        this.initializeWidgets();

        /*Create handle for the RetrofitInstance interface*/
        MyAPIService myAPIService = RetrofitClientInstance.getRetrofitInstance().create(MyAPIService.class);

        Call<List<Spacecraft>> call = myAPIService.getSpacecrafts();
        call.enqueue(new Callback<List<Spacecraft>>() {

            @Override
            public void onResponse(Call<List<Spacecraft>> call, Response<List<Spacecraft>> response) {
                mProgressBar.setVisibility(View.GONE);
                populateListView(response.body());
            }
            @Override
            public void onFailure(Call<List<Spacecraft>> call, Throwable throwable) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(SearchBylplate.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }
}
