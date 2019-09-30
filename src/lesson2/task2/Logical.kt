@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    var flag: Boolean = false
    var buf1: Int
    var buf2: Int
    var buf3: Int
    var buf4: Int
    buf1 = number / 1000
    buf2 = (number - buf1 * 1000) / 100
    buf3 = (number - buf1 * 1000 - buf2 * 100) / 10
    buf4 = number - buf1 * 1000 - buf2 * 100 - buf3 * 10
    if (buf1 + buf2 == buf3 + buf4) flag = true
    return flag
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    var flag: Boolean = false
    if ((x1 == x2) || (y1 == y2) || ((abs(x1 - x2) == abs(y1 - y2)))) flag = true
    return flag
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val Months = arrayOf<Int>(31, 28, 31, 30, 31, 30, 31, 31, 31, 30, 31, 30)
    var flag: Boolean = false
    var result: Int = 0
    if (year % 4 == 0) flag = true
    if (year % 100 == 0) flag = false
    if (year % 400 == 0) flag = true
    for (i in Months.indices) {
        if ((flag == true) && (month == 2)) {
            println()
            result = 29
        } else if (month - 1 == i) {
            result = Months[i]
        }
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    var flag: Boolean = false
    if (sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2)) <= (r2 - r1)) flag = true
    return flag
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    var flag: Boolean = false
    var min1: Int = a
    var min2: Int = b
    if (c < a) {
        min1 = c
        flag = true
    }
    if (flag) {
        if (b > a) min2 = a
    } else {
        if (b > c) min2 = c
    }
    flag = false
    if ((min1 <= r) && (min2 <= s) || (min2 <= r) && (min1 <= s)) flag = true
    return flag
}
