package isabel.vitoria.galeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_tb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opShare:
                dispatchTakePictureIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void dispatchTakePictureIntent() {
    }

    public class MainAdapter extends RecyclerView.Adapter {

        MainActivity mainActivity;
        List<String> photos;

        public MainAdapter(MainActivity mainActivity, List<String> photos) {
            this.mainActivity = mainActivity;
            this.photos = photos;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mainActivity);
            View v = inflater.inflate(R.layout.list_item, parent, false);
            return new MeuViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            ImageView imPhoto = holder.itemView.findViewById(R.id.imItem);
            int w = (int) mainActivity.getResources().getDimension(R.dimen.itemWidth);
            int h = (int) mainActivity.getResources().getDimension(R.dimen.itemHeight);
            Bitmap bitmap = Utils.getBitmap(photos.get(position), w, h);
            imPhoto.setImageBitmap(bitmap);
            imPhoto.setOnClickListener(new View.OnClickListener() {


                @Override
                    public void onClick(View v) {
                    mainActivity.startPhotoActivity(photos.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.photos.size();
        }
    }
}