package cu.elparque.soyric

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        var isPaid = sharedPref.getBoolean("is_paid", false)

        if (!isPaid){
            val response = Verify.isPurchased(this, this.packageName)
//            val userName = response.second
            isPaid = response.first
            val editor = sharedPref.edit()
            editor.putBoolean("is_paid", isPaid)
            editor.commit()
        }

        if (isPaid){
            val imageView: ImageView = findViewById(R.id.imageView)
            Glide.with(this).load(R.drawable.diamond).into(imageView)
            imageView.setOnClickListener {
                Toast.makeText(this, getString(R.string.mantra), Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.should_buy), Toast.LENGTH_LONG).show()
        }

    }
}
