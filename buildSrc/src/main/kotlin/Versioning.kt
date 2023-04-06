/**
 * Generate versionCode and versionName from git tag
 *
 * Use git describe command to generate version information based on latest git tag
 */
@Suppress("MagicNumber")
object Versioning {
    data class Version(
        val major: Int,
        val minor: Int,
        val patch: Int,
        val count: Int = 0,
    ) {
        fun name() = "$major.$minor.$patch"
        fun code() = 1_000_000 * major + 10_000 * minor + 100 * patch + count
    }

    private val regex = """(?<major>\d+)\.(?<minor>\d+)\.(?<patch>\d+)-(?<count>\d+)-(?<hash>\w+)""".toRegex()

    fun version(name: String): Version = parse(name)

    /**
     * Parse input string to [Version] object
     *
     * It is assumed that input string represents output of `git describe` command
     */
    internal fun parse(input: String): Version {
        val groups = regex.find(input)?.groups
        val major = checkNotNull(groups?.get("major")?.value).toInt()
        val minor = checkNotNull(groups?.get("minor")?.value).toInt()
        val patch = checkNotNull(groups?.get("patch")?.value).toInt()
        val count = checkNotNull(groups?.get("count")?.value).toInt()
        return Version(
            major = major,
            minor = minor,
            patch = patch,
            count = count
        )
    }
}
