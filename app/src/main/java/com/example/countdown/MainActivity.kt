package com.example.countdown

// import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Button
import android.widget.ScrollView

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val scrollView = findViewById<ScrollView>(R.id.scrollView)
//
//        val btn = Button(this)
//        btn.text = "Hello World!"
//
//        myRoot.addView(btn)

        var hasData = false
        // for (thing in things) {
        for (i in 0..1) {
            createDateFragment(i.toString())
            hasData = true
        }

        if (!hasData) {
            createDateFragment("0")
        }
    }

    private fun createDateFragment(tag: String) {
        // println("tag $tag")
        val dateFragment = DateFragment()
        // firstDateFragment.arguments = intent.extras;
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.my_root, dateFragment, tag)
        transaction.commit()
    }
}
