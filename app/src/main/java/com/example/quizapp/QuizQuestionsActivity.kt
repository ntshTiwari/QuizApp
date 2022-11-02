package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

//// Why do we use View.OnClickListener => to have all the onClickListeners inside of one function here onClick()
//// Now we can use a switch statement (here, we use when) to have different onClickListeners for different Views
//// StackOverflow => https://stackoverflow.com/questions/30082892/best-way-to-implement-view-onclicklistener-in-android


/// Now our class QuizQuestionsActivity also extends from View.OnClickListener
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var allQuestions: ArrayList<Question>? = null
    private var currentQuestion: Question? = null
    private var currentQuestionNumber: Int? = null

    private var questionTextView: TextView? = null
    private var flagImageView: ImageView? = null

    private var progressBar: ProgressBar? = null
    private var progressBarTextView: TextView? = null

    private var option1TextView: TextView? = null
    private var option2TextView: TextView? = null
    private var option3TextView: TextView? = null
    private var option4TextView: TextView? = null
    private var allTextViews = ArrayList<TextView>()

    private var submitButton: Button? = null

    /// default value is null
    private var selectedOption: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        currentQuestionNumber = 0
        allQuestions = Constants.getQuestions()
        currentQuestion = allQuestions!![currentQuestionNumber!!]

        getAllViews()
        populateAllViews()

        addAllTextViewsToAnArray()
        setOnClickListenerForTextViews()
    }

    /// we are adding all textViews to allTextViews array,
    /// so that we can fo faster calculations
    private fun addAllTextViewsToAnArray() {
        allTextViews.add(option1TextView!!)
        allTextViews.add(option2TextView!!)
        allTextViews.add(option3TextView!!)
        allTextViews.add(option4TextView!!)
    }

    private fun setOnClickListenerForTextViews() {
        for(_textView in allTextViews){
            _textView.setOnClickListener(this)
        }
    }

    private fun getAllViews() {
        questionTextView = findViewById(R.id.questionTextView)
        flagImageView = findViewById(R.id.flagImageView)

        progressBar = findViewById(R.id.progressBar)
        progressBarTextView = findViewById(R.id.progressBarTextView)

        option1TextView = findViewById(R.id.option1TextView)
        option2TextView = findViewById(R.id.option2TextView)
        option3TextView = findViewById(R.id.option3TextView)
        option4TextView = findViewById(R.id.option4TextView)

        submitButton = findViewById(R.id.submitButton)
    }

    private fun populateAllViews() {
        questionTextView?.text = currentQuestion?.question
        flagImageView?.setImageResource(currentQuestion?.image!!)

        progressBar?.progress = currentQuestionNumber!!
        progressBarTextView?.text = "${currentQuestionNumber!! + 1}/${allQuestions!!.size}"

        option1TextView?.text = currentQuestion?.optionOne
        option2TextView?.text = currentQuestion?.optionTwo
        option3TextView?.text = currentQuestion?.optionThree
        option4TextView?.text = currentQuestion?.optionFour
    }

    override fun onClick(view: View?) {
        when(view?.id){

            /// we are writing specific cases for all textViews
            ///  as we don't want it to trigger for anything else, like on click of a button or any other textView
            R.id.option1TextView -> {
                option1TextView?.let {
                    onTextViewSelect(it, 1)
                }
            }

            R.id.option2TextView -> {
                option2TextView?.let {
                    onTextViewSelect(it, 2)
                }
            }

            R.id.option3TextView -> {
                option3TextView?.let {
                    onTextViewSelect(it, 3)
                }
            }

            R.id.option4TextView -> {
                option4TextView?.let {
                    onTextViewSelect(it, 4)
                }
            }
        }
    }

    private fun onTextViewSelect(textView: TextView, _selectedOption: Int) {
        selectedOption = _selectedOption

        println(selectedOption)

        deselectAllTextView(textView)

        textView.setTextColor(Color.parseColor("#363A43"))

        /// Typeface definition => https://developer.android.com/reference/android/graphics/Typeface
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        /// to write it as below case declared in deselectAllTextView
        // textView.typeface = Typeface.DEFAULT_BOLD

        /// ContextCompat definition => https://developer.android.com/reference/androidx/core/content/ContextCompat
        textView.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun deselectAllTextView(textView: TextView) {
        for (_textView in allTextViews){
            _textView.setTextColor(Color.parseColor("#7A8089"))
            _textView.typeface = Typeface.DEFAULT
            /// to write it as above case declared in onTextViewSelect
            // _textView.setTypeface(textView.typeface, Typeface.NORMAL)

            _textView.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
}