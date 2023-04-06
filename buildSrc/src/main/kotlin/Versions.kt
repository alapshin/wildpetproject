import java.util.Locale

object Versions {
    const val minSdk = 23
    const val compileSdk = 33
    const val targetSdk = 33
    const val ndkVersion = "21.3.6528147"
}

fun isNonStable(version: String): Boolean {
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
        version.toUpperCase(Locale.ROOT).contains(it)
    }
    return !(stableKeyword || regex.matches(version))
}
