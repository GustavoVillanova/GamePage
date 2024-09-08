import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.transformIntoAge(): Int{
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var age = 0
    val dateOfBirth = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    age = Period.between(dateOfBirth, today).years
    return age
}