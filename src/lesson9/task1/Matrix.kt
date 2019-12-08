@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    require(!(height <= 0 || width <= 0))
    return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val unit = MutableList(height) { MutableList(width) { e } }

    override fun get(row: Int, column: Int): E = unit[row][column]

    override fun get(cell: Cell): E = unit[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        unit[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        unit[cell.row][cell.column] = value
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> && other.unit == unit

    override fun toString(): String {
        var buffer = ""
        for (iter in unit) {
            for (iter1 in iter.indices) {
                if (iter1 != iter.size - 1) buffer += "${iter[iter1]}/t"
                else buffer += iter[iter1]
            }
            buffer += "/n"
        }
        buffer.dropLast(3)
        return buffer
    }
}

