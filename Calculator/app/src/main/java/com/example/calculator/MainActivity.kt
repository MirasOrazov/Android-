package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    var dot:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener{appendOnExpression("1",true)}
        tvTwo.setOnClickListener{appendOnExpression("2",true)}
        tvThree.setOnClickListener{appendOnExpression("3",true)}
        tvFour.setOnClickListener{appendOnExpression("4",true)}
        tvFive.setOnClickListener{appendOnExpression("5",true)}
        tvSix.setOnClickListener{appendOnExpression("6",true)}
        tvSeven.setOnClickListener{appendOnExpression("7",true)}
        tvEight.setOnClickListener{appendOnExpression("8",true)}
        tvNine.setOnClickListener{appendOnExpression("9",true)}


        tvPlus.setOnClickListener{appendOnExpression2("+",false)}
        tvMinus.setOnClickListener{appendOnExpression2("-",false)}
        tvMultiply.setOnClickListener{appendOnExpression2("*",false)}
        tvDivide.setOnClickListener{appendOnExpression2("/",false)}

        tvSqrt.setOnClickListener{
            if(tvResult.text.isNotEmpty()){
            var c = Math.sqrt(tvResult.text.toString().toDouble())
            tvExpression.text=""
            tvResult.text = String.format("%.6f",c).replace(",",".")
        }
            if(tvExpression.text.isNotEmpty()) {
                var b = Math.sqrt(tvExpression.text.toString().toDouble())
                tvResult.text = String.format("%.6f",b).replace(",",".")
            }
            else tvSqrt.isEnabled
        }

        tvSqr.setOnClickListener{
            if(tvResult.text.isNotEmpty()){
                var v = Math.pow(tvResult.text.toString().toDouble(),2.toDouble())
                tvExpression.text=""
                tvResult.text= String.format("%.1f",v).replace(",",".")
            }
            if(tvExpression.text.isNotEmpty()){
                var v = Math.pow(tvExpression.text.toString().toDouble(),2.toDouble())
                tvResult.text= String.format("%.1f",v).replace(",",".")
            }
            else tvSqr.isEnabled
        }
        tvDot.setOnClickListener{
            dot++
            num=true
            if(dot==1) {
                if (q == true) {
                    if (tvExpression.text.isNotEmpty()) {
                        if (tvResult.text.isNotEmpty()) {
                            tvExpression.text = ""
                        }
                        tvExpression.append(tvResult.text)
                        tvExpression.append(".")
                        tvResult.text = "";

                    }
                }
            }
            else tvDot.isEnabled

        }

        tvClear.setOnClickListener{
            tvExpression.text=""
            tvResult.text=""
            dot=0
            num=false }

        tvDel.setOnClickListener{
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                if(string.substring(string.length-1,string.length)==".") dot=0
                tvExpression.text=string.substring(0,string.length-1) }
            if(tvExpression.text.isEmpty()) num=false
            tvResult.text=""
        }

        tvEqual.setOnClickListener{
            try{
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                var longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text= String.format("%.1f",result).replace(",",".")


            }catch (e:Exception){
                Log.d("Exception","message: "+e.message)
            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.run {
            putString("KEY",tvResult.text.toString())
        }

        outState?.run {
            putString("KEY2",tvExpression.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        tvResult.text = savedInstanceState?.getString("KEY")
        tvExpression.text=savedInstanceState?.getString("KEY2")
    }

    var q:Boolean = false
    var num:Boolean=false

    fun appendOnExpression(string: String, canClear:Boolean){
        q=true
        num=true
        if(tvResult.text.isNotEmpty()){
            tvExpression.text=""
        }

        if(canClear){
            tvResult.text=""
            tvExpression.append(string)
        }else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text="";
        }
    }

    fun tvZeroFun(view:View){
        q=true
        if(tvExpression.text.isEmpty()) {
            tvResult.text = ""
            tvExpression.append("0")
        }
        else if(num==true && tvResult.text.isEmpty()){
            tvResult.text = ""
            tvExpression.append("0")
        }
        else if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
            tvResult.text=""
            tvExpression.append("0")
            tvZero.isEnabled
            num=false
        }
        else tvZero.isEnabled
    }

    fun appendOnExpression2(string: String, canClear:Boolean){
        dot=0
        num=true
        if(q==true) {
            if (tvExpression.text.isNotEmpty()) {
                if (tvResult.text.isNotEmpty()) {
                    tvExpression.text = ""
                }

                if (canClear) {
                    tvResult.text = ""
                    tvExpression.append(string)
                } else {
                    tvExpression.append(tvResult.text)
                    tvExpression.append(string)
                    tvResult.text = "";
                }
            }
        }
        else tvExpression.isEnabled
        q=false

    }

    fun tvSinFun(view: View){
        if(tvResult.text.isNotEmpty()){
            var c = Math.sin(tvResult.text.toString().toDouble()*Math.PI/180)
            tvExpression.text=""
            tvResult.text = c.toString()
        }
        if(tvExpression.text.isNotEmpty()) {
            var b = Math.sin(tvExpression.text.toString().toDouble()*Math.PI/180)
            tvResult.text = b.toString()
        }
        else tvSqrt.isEnabled
    }

    fun tvCosFun(view: View){
        if(tvResult.text.isNotEmpty()){
            var c = Math.cos((tvResult.text.toString().toDouble()*Math.PI)/180)
            tvExpression.text=""
            tvResult.text = c.toString()
        }
        if(tvExpression.text.isNotEmpty()) {
            var b = Math.cos((tvExpression.text.toString().toDouble()*Math.PI)/180)
            tvResult.text = b.toString()
        }
        else tvSqrt.isEnabled
    }

    fun tvTanFun(view: View){
        if(tvResult.text.isNotEmpty()){
            var c = Math.tan(tvResult.text.toString().toDouble()*Math.PI/180)
            tvExpression.text=""
            tvResult.text = c.toString()
        }
        if(tvExpression.text.isNotEmpty()) {
            var b = Math.tan(tvExpression.text.toString().toDouble()*Math.PI/180)
            tvResult.text = b.toString()
        }
        else tvSqrt.isEnabled
    }

    fun tvLogFun(view: View){
        if(tvResult.text.isNotEmpty()){
            var c = Math.log10(tvResult.text.toString().toDouble())
            tvExpression.text=""
            tvResult.text = c.toString()
        }
        if(tvExpression.text.isNotEmpty()) {
            var b = Math.log10(tvExpression.text.toString().toDouble())
            tvResult.text = b.toString()
        }
        else tvSqrt.isEnabled
    }

    fun tvLnFun(view: View){
        if(tvResult.text.isNotEmpty()){
            var c = Math.log(tvResult.text.toString().toDouble())
            tvExpression.text=""
            tvResult.text = c.toString()
        }
        if(tvExpression.text.isNotEmpty()) {
            var b = Math.log(tvExpression.text.toString().toDouble())
            tvResult.text = b.toString()
        }
        else tvSqrt.isEnabled
    }

    fun tvFacFun(view: View){
            if (tvResult.text.isNotEmpty()) {
                if(tvResult.text.isDigitsOnly()) {
                    var c = tvResult.text.toString().toInt()
                    var factorial: Long = 1
                    for (i in 1..c) {
                        factorial *= i.toLong()
                    }
                    tvExpression.text = ""
                    tvResult.text = factorial.toString()
                }
            }
            if (tvExpression.text.isNotEmpty()) {
                if(tvExpression.text.isDigitsOnly()) {
                    var b = tvExpression.text.toString().toInt()
                    var factorial: Long = 1
                    for (i in 1..b) {
                        factorial *= i.toLong()
                    }
                    tvResult.text = factorial.toString()
                }
            }
        else tvSqrt.isEnabled
    }
    fun tvNFun(view: View){
        dot=0
        if(q==true) {
            if (tvExpression.text.isNotEmpty()) {
                if (tvResult.text.isNotEmpty()) {
                    tvExpression.text = ""
                }
                    tvExpression.append(tvResult.text)
                    tvExpression.append("^")
                    tvResult.text = "";

                }
            }
        tvExpression.isEnabled
        q=false
    }
    fun tvCtgFun(view: View){
        if(tvResult.text.isNotEmpty()){
            var c = Math.cos(tvResult.text.toString().toDouble()*Math.PI/180)/Math.sin(tvResult.text.toString().toDouble()*Math.PI/180)
            tvExpression.text=""
            tvResult.text = c.toString()
        }
        if(tvExpression.text.isNotEmpty()) {
            var b = Math.cos(tvExpression.text.toString().toDouble()*Math.PI/180)/Math.sin(tvExpression.text.toString().toDouble()*Math.PI/180)
            tvResult.text = b.toString()
        }
        else tvSqrt.isEnabled
    }



}
