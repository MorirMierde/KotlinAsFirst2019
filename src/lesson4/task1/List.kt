@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var sum = 0.0
    var count = 0.0
    var result: Double
    for (iter in list) {
        sum += iter
        count++
    }
    result = sum / count
    return result
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    var power = 1
    var flag = true
    println(p)
    println(x)
    p.forEach {
        if (flag) {
            flag = false
            result += it
            return@forEach
        }
        result += it * (x.toDouble().pow(power.toDouble())).toInt()
        power++
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return list
    val numbers: MutableList<Int> = mutableListOf()
    //var count: Int = 0
    for (iter1 in list.indices) {
        var summ = 0
        for (iter2 in list.indices) {
            if (iter1 >= iter2) {
                summ += list[iter2]
            }
        }
        numbers.add(summ)
    }
    for (i in list.indices) {
        list[i] = numbers[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var number = n
    var factor = 2
    while (factor <= number) {
        if (number % factor == 0) {//если делится нацело
            number /= factor
            result.add(factor)
            factor = 1
        }
        factor++
    }
    result.sort()
    result.toList()
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val buffer = mutableListOf<Int>()
    println(n)
    var number = n
    var factor = 2
    var result = ""
    var flag = true

    while (factor <= number) {
        if (number % factor === 0) {//если делится нацело
            number /= factor
            buffer.add(factor)
            factor = 1
        }
        factor++
    }
    buffer.sort()
    buffer.forEach {
        if (flag) {
            result += it.toString()
            flag = false
            return@forEach
        }
        result += "*"
        result += it.toString()
    }
    return result
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    var buffer = mutableListOf<Int>()
    buffer.add(number % base)
    while (number >= base) {
        println(number % base)
        number /= base
        buffer.add(number % base)
    }
    buffer.reverse()
    return buffer
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val buffer = digits.toMutableList()
    buffer.reverse()
//    var balance = 0
    var power = 0
    var result = 0
    buffer.forEach {
        result += it * (base.toDouble().pow(power.toDouble())).toInt()
        power++
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result = ""
    var thousand = 0
    var hundred = 0
    var buf1 = 0
    var buf2 = 0
    var buf3 = 0
    val map1: List<String> = listOf(
        "сто ",
        "двести ",
        "триста ",
        "четыреста ",
        "пятьсот ",
        "шестьсот ",
        "семьсот ",
        "восемьсот ",
        "девятьсот "
    )
    val map2: List<String> = listOf(
        "десять ",
        "двадцать ",
        "тридцать ",
        "сорок ",
        "пятьдесят ",
        "шестьдесят ",
        "семьдесят ",
        "восемьдесят ",
        "девяносто "
    )
    val map3: List<String> = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val map4: List<String> = listOf(
        "одиннадцать ",
        "двенадцать ",
        "тринадцать ",
        "четырнадцать ",
        "пятнадцать ",
        "шестнадцать ",
        "семнадцать ",
        "восемнадцать ",
        "девятнадцать "
    )
    val map5: List<String> = listOf(
        "одна тысяча ",
        "две тысячи ",
        "три тысячи ",
        "четыре тысячи ",
        "пять тысяч ",
        "шесть тысяч ",
        "семь тысяч ",
        "восемь тысяч ",
        "девять тысяч "
    )

    println(n)
    thousand = n / 1000
    if (thousand != 0) {
        buf1 = thousand / 100
        println(buf1)
        if (buf1 != 0) {
            result += map1[buf1 - 1]
            if (thousand - buf1 * 100 == 0) result += "тысяч "
        }
        println(result)
        buf2 = thousand - buf1 * 100
        println(buf2)
        println("thousand:  $thousand")
        if (buf2 in 11..19) {
            buf2 -= 10
            println(buf2)
            result += map4[buf2 - 1] + "тысяч "
        } else if (buf2 / 10 == 0) {
            buf2 %= 10
            println(buf2)
            if (buf2 != 0) {
                result += map5[buf2 - 1]
            }
        } else {
            buf2 /= 10
            println(buf2)
            result += map2[buf2 - 1]
            buf3 = thousand - buf1 * 100 - buf2 * 10
            println(buf3)
            result += if (buf3 != 0) {
                map5[buf3 - 1]
            } else {
                "тысяч "
            }
        }
    }
    hundred = n - thousand * 1000
    if (hundred != 0) {
        buf1 = hundred / 100
        println(buf1)
        if (buf1 != 0) {
            result += map1[buf1 - 1]
        }
        println(result)
        buf2 = hundred - buf1 * 100
        println(buf2)
        println("hundred:  $hundred")
        if (buf2 in 11..19) {
            buf2 -= 10
            println(buf2)
            result += map4[buf2 - 1]
        } else if (buf2 / 10 == 0) {
            buf2 %= 10
            println(buf2)
            if (buf2 != 0) {
                result += map3[buf2 - 1]
            }
        } else {
            buf2 /= 10
            println(buf2)
            result += map2[buf2 - 1]
            buf3 = hundred - buf1 * 100 - buf2 * 10
            println(buf3)
            if (buf3 != 0) {
                result += map3[buf3 - 1]
            }
        }
    }
    if (result.last() == ' ') result = result.dropLast(1)
    println(hundred)
    println(thousand)
    return result
}