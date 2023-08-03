package id.avang.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import id.avang.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumberClicked()
        onOperatorClicked()
    }

    private fun onOperatorClicked() {
        binding.btnBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnBasteh.setOnClickListener {
            appendText(")")
        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '/' &&
                    myChar != '*'
                ) {

                    appendText("/")
                }
            }
        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '/' &&
                    myChar != '*'
                ) {
                    appendText("*")
                }
            }
        }
        binding.btnSum.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '/' &&
                    myChar != '*'
                ) {
                    appendText("+")
                }
            }
        }
        binding.btnTafrig.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '/' &&
                    myChar != '*'
                ) {
                    appendText("+")
                }
            }
        }
        binding.btnAc.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }
        binding.btnpakidn.setOnClickListener {
            val oldText = binding.txtExpression.text.toString()
            if (oldText.isNotEmpty()) {
                binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
            }
        }
        binding.btnMosavi.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }
            } catch (e: Exception) {
                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this, "داداش داری اشتباه میزنی:)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onNumberClicked() {
        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }

        binding.btndat.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")
                || binding.txtExpression.text.contains("+")
                || binding.txtExpression.text.contains("-")
                || binding.txtExpression.text.contains("*")
                || binding.txtExpression.text.contains("/")
            ) {
                appendText(".")

            }
        }

    }

    private fun appendText(newText: String) {
        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtJavab.text = ""
        binding.txtExpression.append(newText)
        val viewTree: ViewTreeObserver = binding.scrollHori.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.scrollHori.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.scrollHori.scrollTo(binding.txtExpression.width, 0)
            }
        })
    }

}