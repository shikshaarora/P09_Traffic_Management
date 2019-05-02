package cultoftheunicorn.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.opencv.cultoftheunicorn.marvel.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    TextView LnoDetailTextView,commentsDetailTextView,countDetailTextView,dateDetailTextView,
            crimeDetailTextView,placeDetailTextView,LplateDetailTextView,nameDetailTextView,amountDetailTextView;

    ImageView teacherDetailImageView;

    private void initializeWidgets(){
        LnoDetailTextView= (TextView) findViewById(R.id.LnoDetailTextView);
        commentsDetailTextView= (TextView) findViewById(R.id.commentsDetailTextView);
        dateDetailTextView= (TextView) findViewById(R.id.dateDetailTextView);
        crimeDetailTextView= (TextView) findViewById(R.id.crimeDetailTextView);
        countDetailTextView= (TextView) findViewById(R.id.countDetailTextView);
        teacherDetailImageView= (ImageView) findViewById(R.id.teacherDetailImageView);
        placeDetailTextView= (TextView) findViewById(R.id.placeDetailTextView);
        LplateDetailTextView= (TextView) findViewById(R.id.LplateDetailTextView);
        nameDetailTextView= (TextView) findViewById(R.id.nameDetailTextView);
        amountDetailTextView= (TextView) findViewById(R.id.amountDetailTextView);


    }
    private String getDateToday(){
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();
        String today= dateFormat.format(date);
        return today;
    }
    private String getRandomDestination(){
        String[] destinations={"VV Sephei","KY Cygni","Polaris","Betelgeuse","Aldebaran"};
        Random random=new Random();
        int index=random.nextInt(destinations.length-1);
        return destinations[index];
    }

    private void receiveAndShowData(){
        //RECEIVE DATA FROM ITEMS ACTIVITY VIA INTENT
        Intent i=this.getIntent();
        String lno=i.getExtras().getString("lno_KEY");
        String Comments=i.getExtras().getString("Comments_KEY");
        String Violation=i.getExtras().getString("Violation_KEY");
                String imageURL=i.getExtras().getString("IMAGE_KEY");
        String technologyExists=i.getExtras().getString("TECHNOLOGY_EXISTS_KEY");
        String count=i.getExtras().getString("count_KEY");
        String place=i.getExtras().getString("place_KEY");
        String Lplate=i.getExtras().getString("Lplate_KEY");
        String date=i.getExtras().getString("date_KEY");
        String amount=i.getExtras().getString("amount_KEY");
        String name=i.getExtras().getString("name_KEY");

        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
        LnoDetailTextView.setText(lno);
        commentsDetailTextView.setText(Comments);
        dateDetailTextView.setText(date);
        crimeDetailTextView.setText(Violation);
        countDetailTextView.setText(count);
        placeDetailTextView.setText(place);
        LplateDetailTextView.setText(Lplate);
        amountDetailTextView.setText(amount);
        nameDetailTextView.setText(name);


        Picasso.get().load(imageURL).placeholder(R.drawable.placeholder).into(teacherDetailImageView);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeWidgets();
        receiveAndShowData();
    }
}