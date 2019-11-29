@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = TODO()


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    var maximum = 0
    val outputStream = File(outputName).bufferedWriter()
    for (line in File(inputName).readLines()) {
        if (line.trim().length > maximum) maximum = line.trim().length
    }
    for (line in File(inputName).readLines()) {
        if (line.trim().length < maximum) {
            val buffer = (maximum - line.trim().length) / 2
            outputStream.write(" ".repeat(buffer) + line.trim())
            outputStream.newLine()
        } else {
            outputStream.write(line.trim())
            outputStream.newLine()
        }
    }
    outputStream.close()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var maximum = 0
    for (line in File(inputName).readLines()) {
        if (line.trim().length > maximum) maximum = line.trim().length
    }
    for (line in File(inputName).readLines()) {
        if (line.trim().length == maximum) {
            outputStream.write(line.trim())
            outputStream.newLine()
        } else {
            var wordcharcount = 0// часть строки занятая символами
            val spacecount = line.trim().split(' ').filter { it != "" }.size - 1//число промежутков между словами
            if (spacecount != 0) {
                var buffer = 0
                for (word in line.trim().split(' ').filter { it != "" }) {
                    wordcharcount += word.length
                }
                val space = maximum - wordcharcount//общая масса пробелов
                val spaceunit = space / spacecount
                var balance = space - spaceunit * spacecount
                var string = ""
                if (balance != 0) {
                    for (word in line.trim().split(' ').filter { it != "" }) {
                        string += word
                        string += if (balance > 0) {
                            " ".repeat(spaceunit + 1)
                        } else {
                            " ".repeat(spaceunit)
                        }
                        balance--
                    }
                } else {
                    for (word in line.trim().split(' ').filter { it != "" }) {
                        string += word
                        string += " ".repeat(spaceunit)
                    }
                }
                outputStream.write(string.trim())
                outputStream.newLine()
            } else {
                if (line.trim().isNotEmpty()) {
                    outputStream.write(line.trim())
                    outputStream.newLine()
                } else {
                    outputStream.newLine()
                }
            }
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Фрукты
<ol>
<li>Бананы</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {

    val outputStream = File(outputName).bufferedWriter()
    var answer = mutableListOf<String>()
    var sum = 0
    var buffer = mutableListOf<Int>()
    var buf = rhv
    while (buf != 0) {
        buffer.add(buf % 10)
        buf /= 10
    }
    var k = 1
    buffer.forEach {
        answer.add((lhv * it).toString())
        sum += lhv * it * k
        k *= 10
    }
    var long = sum.toString().length + 1
    outputStream.write(" ".repeat(long - lhv.toString().length) + lhv)
    outputStream.newLine()
    outputStream.write("*" + " ".repeat(long - rhv.toString().length - 1) + rhv)
    outputStream.newLine()
    outputStream.write("-".repeat(long))
    outputStream.newLine()
    for (iter in answer.indices) {
        if (iter == 0) outputStream.write(" ".repeat(long - answer[iter].length) + answer[iter])
        else {
            val space = long - answer[iter].length - 1
            println(space)
            outputStream.newLine()
            outputStream.write("+" + " ".repeat(space - iter) + answer[iter])
        }
    }
    outputStream.newLine()
    outputStream.write("-".repeat(long))
    outputStream.newLine()
    outputStream.write(" $sum")
    println(long)
    println(buffer)
    println(answer)
    println(sum)
    outputStream.close()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
/*
    val outputStream = File(outputName).bufferedWriter()
    var longspace = " $lhv | ".length

    var number = mutableListOf<Int>()
    var buf = lhv
    var result = 0
    while (buf != 0) {
        number.add(buf % 10)
        buf /= 10
    }
    var difference = 0
    var resulted = 0
    var answer = mutableListOf<String>()
    number = number.reversed().toMutableList()
    var flag = false
    for (iter in number) {
        difference = difference * 10 + iter
        if (difference < rhv) {
            if (flag) {
                if (difference < 10) {
                    answer.add("0$difference")
                } else {
                    answer.add("$difference")
                }
                answer.add("-0")
            }
            result *= 10
        }
        if (difference == rhv) {
            if (rhv < 10) {
                answer.add("0$rhv")
            } else {
                answer.add("$rhv")
            }
            answer.add("-$rhv")
            difference = 0
            result *= 10
            result += 1
            flag = true
        }
        if (difference > rhv) {
            if (difference < 10) {
                answer.add("0$difference")
            } else {
                answer.add("$difference")
            }
            buf = difference / rhv
            answer.add("-${rhv * buf}")
            difference -= buf * rhv
            result *= 10
            result += buf
            flag = true
        }
    }
    answer = answer.reversed().dropLast(1).reversed().toMutableList()
    var shortspace = 0
    var strlhg = 0
    if (answer.size == 1 && answer.first().length == lhv.toString().length) {
        outputStream.write("$lhv | $rhv")
        longspace -= 1
        strlhg = "$lhv".length
    } else if (rhv.toString().length >= lhv.toString().length && lhv.toString().length > 1 && rhv > lhv) {
        outputStream.write("$lhv | $rhv")
        longspace -= 1
        strlhg = "$lhv".length
    } else {
        outputStream.write(" $lhv | $rhv")
        strlhg = " $lhv".length
    }
    if (rhv > lhv) {
        if (rhv.toString().length >= lhv.toString().length && lhv.toString().length > 1) {
            var specialspace = lhv.toString().length - 2
            println(specialspace)
            outputStream.newLine()
            if (specialspace > 0) outputStream.write(" ".repeat(specialspace) + "-0   0")
            else outputStream.write("-0" + " ".repeat(longspace - 2) + "0")
            outputStream.newLine()
            if (specialspace > 0) outputStream.write("--" + "-".repeat(specialspace)) else outputStream.write("--")
            outputStream.newLine()
            outputStream.write("$lhv")
        } else {
            outputStream.newLine()
            outputStream.write("-0" + " ".repeat(longspace - 2) + "0")
            outputStream.newLine()
            outputStream.write("--")
            outputStream.newLine()
            outputStream.write(" $lhv")
        }
    }
    println(answer)

    for (iter in answer.indices) {
        if (iter == 0) {
            outputStream.newLine()
            longspace -= answer[iter].length
            outputStream.write(answer[iter] + " ".repeat(longspace) + result)
            outputStream.newLine()
            outputStream.write("-".repeat(answer[iter].length))
            shortspace = answer[iter].length - 1
        } else {
            println(shortspace)
            println(strlhg)
            if (shortspace + answer[iter].length > strlhg) {
                println("*************************")
                shortspace = strlhg - 2
            }
            if (answer[iter].contains('-') && answer[iter].length > answer[iter - 1].length) shortspace -= 1
            outputStream.newLine()
            outputStream.write(" ".repeat(shortspace) + answer[iter])
            if (answer[iter].contains('-') && answer[iter].length > answer[iter - 1].length) shortspace += 1
            if (answer[iter].contains('-')) {

                if (!answer[iter].contains("-0") && answer[iter].length > answer[iter - 1].length) {
                    shortspace -= 1
                }
                outputStream.newLine()
                outputStream.write(" ".repeat(shortspace) + "-".repeat(answer[iter].length))
                if (!answer[iter].contains("-0")) {
                    shortspace += answer[iter - 1].length - 1
                    if (shortspace > strlhg - 1) {
                        shortspace = strlhg - 1
                    }
                }
            }
        }
        if (iter == answer.size - 1) {

            if (answer.size == 1 && answer[iter].length > lhv.toString().length) {
                shortspace = answer[iter].length - difference.toString().length - 1
            }
            if (iter != 0 && answer[iter - 1] == "00") shortspace += 1
            outputStream.newLine()
            println()
            if (shortspace >= strlhg - 2) {
                shortspace = strlhg - 2
            }
            outputStream.write(" ".repeat(shortspace + 1) + difference)
        }

    }
    outputStream.close()*/
}

