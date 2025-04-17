import java.util.Scanner

// Клас власного винятку
class InvalidExpenseException(message: String) : Exception(message)

fun main() {
    val scanner = Scanner(System.`in`)
    val expenses = mutableListOf<Double>()

    println("Введіть витрати за 7 днів:")

    var day = 1
    while (day <= 7) {
        print("День $day: ")
        try {
            val input = scanner.nextLine().toDouble()
            if (input < 0) throw InvalidExpenseException("Витрата не може бути від’ємною!")
            expenses.add(input)
            day++
        } catch (e: InvalidExpenseException) {
            println("Помилка: ${e.message}")
        } catch (e: Exception) {
            println("Неправильне введення. Введіть число.")
        }
    }

    // Загальні витрати
    val total = expenses.sum()

    // Найбільша витрата 
    val maxExpense = expenses.maxOrNull() ?: 0.0
    val maxDayIndex = expenses.indexOf(maxExpense) + 1

    // Середня витрата
    val average = total / expenses.size

    // Результати
    
    val evaluation = when {
        total < 500 -> "Економно"
        total in 500.0..999.99 -> "Помірно"
        else -> "Занадто багато"
    }

    println("\n===== Результати =====")
    println("Загальні витрати: $total грн")
    println("Найбільша витрата: $maxExpense грн (день $maxDayIndex)")
    println("Середня витрата: %.2f грн".format(average))
    println("Оцінка: $evaluation")
}
