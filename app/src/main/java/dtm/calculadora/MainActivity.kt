package dtm.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    //operatuons to do
    val add ="+"
    val sub ="-"
    val mul ="x"
    val division ="/"
    val percentage ="%"

    //current operation
    private var currentOperation=""

    //numbers
    var firstNumber:Double=Double.NaN
    var secondNumber:Double=Double.NaN

    //
    lateinit var temporal_tv:TextView
    lateinit var final_tv:TextView

    //
    lateinit var format:DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        format= DecimalFormat( "#.#############")
        temporal_tv= findViewById(R.id.temporal_tv)
        final_tv= findViewById(R.id.result_tv)
    }

    fun changeOperator(b: View){
        if(temporal_tv.text.toString()!="0" || firstNumber.toString()!="NaN"){
            calculate()
            val button:Button=b as Button
            if(button.text=="÷"){
                currentOperation=division
            }else if(button.text=="X")
                currentOperation=mul
            else
                currentOperation=button.text.toString().trim()
            final_tv.text=format.format(firstNumber)+currentOperation
            temporal_tv.text="0"
        }
    }

    private fun calculate(){
        try{
            if("NaN"!=firstNumber.toString()){
                if(temporal_tv.text.toString()=="0"){
                    temporal_tv.text=final_tv.text.toString()
                }
                secondNumber= temporal_tv.text.toString().toDouble()
                temporal_tv.text=""
                when(currentOperation){
                    add -> firstNumber += secondNumber
                    sub -> firstNumber -= secondNumber
                    mul -> firstNumber *= secondNumber
                    division -> firstNumber /= secondNumber
                    percentage -> firstNumber %= secondNumber
                }
            }else{
                //first number has no value
                firstNumber=temporal_tv.text.toString().toDouble()
            }

        }catch (e:Exception){
            // Aquí puedes manejar el error o imprimirlo para depuración
        }
    }

    fun selectNumber(b: View){
        val button:Button=b as Button
        if(temporal_tv.text.toString()=="0"){
            temporal_tv.text=""
        }
        temporal_tv.text=temporal_tv.text.toString() + button.text.toString()

    }

    fun delete(b: View){
        val button:Button=b as Button
        if(button.text=="C"){
            if(temporal_tv.text.toString().isNotEmpty() && temporal_tv.text.toString().length>1){
                var currentVal:CharSequence = temporal_tv.text.toString() as CharSequence
                temporal_tv.text=currentVal.subSequence(0,currentVal.length-1)
            }else{
                reset()
            }
        }else if(button.text=="CA" || temporal_tv.text.toString().length==1) {
            reset()
        }
    }

    private fun reset(){
        firstNumber=Double.NaN
        secondNumber= Double.NaN
        temporal_tv.text="0"
        final_tv.text="0"
    }

    fun calculateResult(b: View){
        calculate()
        final_tv.text= format.format(firstNumber)
        currentOperation=""
        temporal_tv.text="0"
    }


}