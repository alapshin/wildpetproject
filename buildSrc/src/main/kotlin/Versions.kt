import java.util.Locale

fun isNonStable(version: String): Boolean {
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
        version.uppercase(Locale.ROOT).contains(it)
    }
    return !(stableKeyword || regex.matches(version))
}
