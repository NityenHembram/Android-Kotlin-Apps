package com.amvlabs.androidkotlinapps.emicalculator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.amvlabs.androidkotlinapps.R
import kotlinx.coroutines.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

const val TAG = "tag"

class MainActivityEmiCalculator : AppCompatActivity() {

    private lateinit var chart: PieChart


    private lateinit var la_seek: SeekBar
    private lateinit var te_seek: SeekBar
    private lateinit var ir_seek: SeekBar

    private lateinit var la_start: TextView
    private lateinit var la_end: TextView

    private lateinit var te_start: TextView
    private lateinit var te_end: TextView

    private lateinit var ir_start: TextView
    private lateinit var ir_end: TextView

    private lateinit var mhl:TextView
    private lateinit var pa:TextView
    private lateinit var ia:TextView
    private lateinit var tap:TextView

    private lateinit var details:Button

    var all = ArrayList<Transection>()



//P = Principal loan amount
//N = Loan tenure in months
//R = Monthly interest rate

    private var p3= 25
    private var n1:Double = 20.0
    private var r1:Double = 6.7


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        setContentView(R.layout.activity_main_emicalculator)

        initialising()

        getLoanAmt()
        getTenureAmt()
        getInterestRate()

        findViewById<EditText>(R.id.ei).addTextChangedListener{
            if(it.isNullOrBlank() or it.isNullOrEmpty()){
                la_seek.progress = 0
            }else{
                la_seek.progress = (it).toString().toInt()/100000
                if(it?.length!! >= 6){
                    val lll = it.toString().toInt()/100000
                    updateUI(p = lll,n1,r1)
                }
            }
//            la_seek.progress = it
            Log.d(TAG, "onCreate: $it")
        }
        details.setOnClickListener{
            Log.d(TAG, "onCreate: $p3")
            allData(p3,n1,r1)
            val i = Intent(this,DetailActivity::class.java)
//            i.putParcelableArrayListExtra("key",all);
            i.putExtra("principal",p3)
            i.putExtra("time",n1)
            i.putExtra("rate",r1)
            startActivity(i)
        }
        updateUI()
    }



    private fun getEmi(p: Int, n: Double, r: Double):Double {
        //P x R x (1+R)^N / [(1+R)^N-1] where-
        val pp = p*100000
        var emi:Double = 0.0
        val t = n*12
        emi = if(r != 0.0){
            val R = (r/12)/100
            (pp*R*Math.pow(1+R,t)) /  ((Math.pow(1+R,t)-1))
        }else{
            val v = n/12
            p / t
        }
        return emi
    }

    private fun getInterestRate() {
        ir_seek.max = 1500
        ir_seek.min = 4
        var gh = 0.0
            ir_seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                 gh =  p1/100.0
                r1 = gh
                Log.d(TAG, "onProgressChanged: $gh")
                ir_start.text  = gh.toString()
                

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
//                 r1 = (p0?.progress!!/100).toDouble()
//                Log.d(TAG, "onStopTrackingTouch: $p3  $n1  $r1")
                updateUI(p3,n1,r = r1)
            }

        })

    }

    private fun getTenureAmt() {
        te_seek.min = 1
        te_seek.max = 30
        te_seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                te_start.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {
                n1 = p0!!.progress.toDouble()
//                Log.d(TAG, "ten: $p  $n  $r")

                updateUI(p3,n = n1,r1)
            }
        })
    }

    private fun getLoanAmt() {
        la_seek.max = 100;
        la_seek.min = 1

        la_seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                if(progress.toString().length>2){
                    la_start.text  = "₹${(progress/100).toString()}cr"
                }else{
                    la_start.text  = "₹${progress.toString()} L"
                }

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {
                 p3 = p0!!.progress
//                Log.d(TAG, "lon: $p  $n  $r")
                updateUI(p = p3,n1,r1)
            }
        })

    }

   private fun updateUI(p: Int =25,n: Double = 20.0 ,r: Double =6.7){

       Log.d(TAG, "updateUI: $p  $n  $r")
       la_seek.progress = p
       te_seek.progress = n.toInt()
       ir_seek.progress = (r*100).toInt()

       val emi = getEmi(p,n,r).toInt()
//       Log.d(TAG, "updateUI: $emi")
       val totalAmount = (emi * (n*12)).toInt()
       val principal = p * 100000
       val interest = (totalAmount - principal).toInt()


       mhl.text = "₹${emi.toString()}"
       pa.text = "₹${principal.toString()}"
       ia.text = "₹${interest.toString()}"
       tap.text = "₹${totalAmount.toString()}"

       chart.clearChart()
       chart.addPieSlice(PieModel("Work", principal.toFloat(), Color.parseColor("#FF3700B3")))
       chart.addPieSlice(PieModel("Eating", interest.toFloat(), Color.parseColor("#FFEB3B")))
       chart.startAnimation()

    }

      private  fun allData(p: Int, n: Double, r: Double) {
          val emi = getEmi(p,n,r).toInt()
//       Log.d(TAG, "updateUI: $emi")
          val totalAmount = (emi * (n*12)).toInt()
          val principal = p * 100000
          val interest = (totalAmount - principal).toInt()


          all.add(Transection(principal.toString(),
            emi.toString(),
            interest.toString(),
            interest.toString(),
            totalAmount.toString()))
    }

    private fun initialising() {
        chart = findViewById(R.id.pie_chart)

        la_seek = findViewById(R.id.la_seek)
        te_seek = findViewById(R.id.te_seek)
        ir_seek = findViewById(R.id.ir_seek)

        la_start = findViewById(R.id.la_start)
        la_end = findViewById(R.id.la_end)

        te_start = findViewById(R.id.te_start)
        te_end = findViewById(R.id.te_end)

        ir_start = findViewById(R.id.ir_start)
        ir_end = findViewById(R.id.ir_end)

        mhl = findViewById(R.id.lone_text)
        pa = findViewById(R.id.principal_text)
        ia = findViewById(R.id.interest_text)
        tap = findViewById(R.id.total_text)

        details = findViewById(R.id.view_detail_btn)



    }
}


//P x R x (1+R)^N / [(1+R)^N-1] where-
//
//P = Principal loan amount
//
//N = Loan tenure in months
//
//R = Monthly interest rate
//
//The rate of interest (R) on your loan is calculated per month.
//
//R = Annual Rate of interest/12/100
//
//For example, if rate of interest is 7.2% p.a. then r = 7.2/12/100 = 0.006
//
//If a person avails a loan of ₹10,00,000 at an annual interest rate of 7.2% for a tenure of 120 months (10 years), then his EMI will be calculated as under:
//
//EMI= ₹10,00,000 * 0.006 * (1 + 0.006)120 / ((1 + 0.006)120 - 1) = ₹11,714.
//
//The total amount payable will be ₹11,714 * 120 = ₹14,05,703. Principal loan amount is ₹10,00,000 and the Interest amount will be ₹4,05,703
//
//Calculating the EMI manually using the formula can be tedious.
//
//HDFC’s EMI Calculator can help you calculate your loan EMI with ease.