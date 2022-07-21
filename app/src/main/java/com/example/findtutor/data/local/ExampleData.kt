package com.example.findtutor.data.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.example.findtutor.R
import com.example.findtutor.data.entities.*
import java.io.ByteArrayOutputStream

class ExampleData(context: Context) {
    var users:List<User>
    var subjects:List<Subject>
    init {
        var avatar = ByteArrayOutputStream().let {
            var drawable = context.getDrawable(R.drawable.avatar)
            var bitmap = (drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG,100,it)
            it.toByteArray()
        }
        subjects = listOf(
            Subject(1,"Математика","математике"),
            Subject(2,"История","истории"),
            Subject(3,"Английский язык","английскому языку")
        )
        users = listOf(
            User(1,true,avatar,"Ольга","Иванова",
                "ivanova.olga@gmail.com","+78129708895","7rHTolBuaD",
                "id183430638",1,5.0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                64.529151,40.556052
            ),
            User(2,true,avatar,"Владимир","Смирнов",
                "smirnov.vladimir@gmail.com","+78129793895","7rHTolBuaD",
                "id183430638",2,2.0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                64.532921,40.546299
            ),
            User(3,true,avatar,"Максим","Попов",
                "popov.maksim@gmail.com","+78129793895","7rHTolBuaD",
                "id183430638",3,4.0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                64.533921,40.546315
            )
        )

    }
}