/**
 * Generate versionCode and versionName from git tag
 *
 * Use git describe command to generate version information based on latest git tag
 */
object Versioning {
    private const val HASH_LENGTH = 15

    data class Version(
        val major: Int,
        val minor: Int,
        val patch: Int
    ) {
        fun name() = "$major.$minor.$patch"
        fun code(offset: Int = 0) = 1_000_000 * major + 10_000 *  minor + 100 * patch + offset
    }

    private val regex = Regex("""(?<major>\d+)\.(?<minor>\d+)\.(?<patch>\d+)""")

    fun commit(): String {
        return execute("git rev-parse --short=${HASH_LENGTH} HEAD")
    }

    fun version(name: String): Version  = parse(name)

    /**
     * Parse input string to [Version] object
     *
     * It is assumed that input string represents output of `git describe` command
     */
    internal fun parse(input: String): Version {
        val groups = regex.matchEntire(input)?.groups
        val major = checkNotNull(groups?.get("major")?.value).toInt()
        val minor = checkNotNull(groups?.get("minor")?.value).toInt()
        val patch = checkNotNull(groups?.get("patch")?.value).toInt()
        return Version(
            major = major,
            minor = minor,
            patch = patch
        )
    }

    private fun execute(command: String): String {
        return Runtime.getRuntime().exec(command).inputStream.bufferedReader().readText().trim()
    }
}
