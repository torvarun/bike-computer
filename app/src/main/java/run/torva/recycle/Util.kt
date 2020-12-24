package run.torva.recycle

fun timeToFormattedString(timeInSeconds : Long) : String {
    var time = timeInSeconds

    val hours = time / (60L * 60L)
    time %= (60L * 60L)

    val minutes = time / 60L
    time %= 60L // seconds

    var output = ""

    if (hours > 0) {
        output += "$hours:"
    }

    if (minutes < 10L) {
        output += "0"
    }
    output += "$minutes:"

    if (time < 10L) {
        output += "0"
    }
    output += "$time"

    return output
}