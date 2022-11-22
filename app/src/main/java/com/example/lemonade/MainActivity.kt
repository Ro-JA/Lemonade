/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lemonade

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    /**
     * DO NOT ALTER ANY VARIABLE OR VALUE NAMES OR THEIR INITIAL VALUES.
     *
     * Anything labeled var instead of val is expected to be changed in the functions but DO NOT
     * alter their initial values declared here, this could cause the app to not function properly.
     */
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has been drunk and the glass is empty
    private val RESTART = "restart"

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }
        // === END IF STATEMENT ===

        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()
        lemonImage!!.setOnClickListener {
            // TODO: call the method that handles the state when the image is clicked
//            вызовите метод, который обрабатывает состояние при щелчке по изображению
            clickLemonImage()
            setViewElements()
        }
        lemonImage!!.setOnLongClickListener {
            // TODO: replace 'false' with a call to the function that shows the squeeze count
            // TODO: замените 'false' вызовом функции, которая показывает количество сжатий
            showSnackbar()
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    /**
     * Clicking will elicit a different response depending on the state.
     * This method determines the state and proceeds with the correct action.
     */
    private fun clickLemonImage() {
/*     ЧТО ДЕЛАТЬ: используйте условное выражение типа "если" или "когда" для отслеживания состояния лимонада
* при нажатии на изображение нам, возможно, потребуется изменить состояние на следующий шаг в процессе  приготовления
* лимонада (или, по крайней мере, внести некоторые изменения в текущее состояние в
* случае выжимания лимона). Это должно быть сделано в этом условном выражении
 */
        // ЧТО ДЕЛАТЬ: При нажатии на изображение в состоянии ВЫБОРА состояние должно стать СЖАТЫМ
        // Переменная размера лимона должна быть установлена с помощью метода 'pick()' в классе Lemon Tree
        // Количество выжиманий должно быть равно 0, так как мы еще не выжали ни одного лимона.
        when (lemonSize) {
            -1 -> {
                lemonadeState = SQUEEZE
                lemonSize = lemonTree.pick()
                squeezeCount = 0
            }
            in 2..4 -> {
                lemonSize--
                squeezeCount++
            }
            1 -> {
                lemonadeState = DRINK
                lemonSize--
            }
            0 -> {
                lemonadeState = RESTART
                lemonSize--
            }
        }

        // ЧТО НУЖНО СДЕЛАТЬ: При нажатии на изображение в состоянии СЖАТИЯ количество сжатий должно быть
        // УВЕЛИЧЕНО на 1, а размер лимона должен быть уменьшен на 1.
        // Если размер лимона достиг 0, из него выжали сок, и состояние должно стать НАПИТКОМ
        // Кроме того, размер лимона больше не имеет значения и должен быть установлен на -1

        // ЧТО ДЕЛАТЬ: При нажатии на изображение в состоянии НАПИТКА состояние должно стать ПЕРЕЗАПУСКОМ

        // ЧТО ДЕЛАТЬ: При нажатии на изображение в состоянии ПЕРЕЗАПУСКА состояние должно стать SELECT

        // ЧТО НУЖНО СДЕЛАТЬ: наконец, перед завершением функции нам нужно настроить элементы представления так, чтобы
// пользовательский интерфейс мог отражать правильное состояние
    }

    /**
     * Set up the view elements according to the state.
     */
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        // // ЗАДАЧА: настройте условие, которое отслеживает состояние лимонада

        // // ЧТО НУЖНО СДЕЛАТЬ: для каждого состояния textAction TextView должно быть установлено в соответствующую строку из
        // файл string resources. Строки названы в соответствии с состоянием

        // // ЧТО НУЖНО СДЕЛАТЬ: Дополнительно, для каждого состояния, изображение лимона должно быть установлено на соответствующее
        // извлекается из доступных ресурсов. Объекты рисования имеют те же имена, что и строки
        // но помните, что это чертежи, а не строки.
        when (lemonadeState) {
            SELECT -> {
                lemonImage?.setImageResource(R.drawable.lemon_tree)
                textAction.setText(R.string.lemon_select)
            }
            SQUEEZE -> {
                lemonImage?.setImageResource(R.drawable.lemon_squeeze)
                textAction.setText(R.string.lemon_squeeze)
            }
            DRINK -> {
                lemonImage?.setImageResource(R.drawable.lemon_drink)
                textAction.setText(R.string.lemon_drink)
            }
            RESTART -> {
                lemonImage?.setImageResource(R.drawable.lemon_restart)
                textAction.setText(R.string.lemon_empty_glass)
            }
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * Long clicking the lemon image will show how many times the lemon has been squeezed.
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_Layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}

/**
 * A Lemon tree class with a method to "pick" a lemon. The "size" of the lemon is randomized
 * and determines how many times a lemon needs to be squeezed before you get lemonade.
 */
class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}
