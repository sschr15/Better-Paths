@file:Suppress("unused")

package sschr15.tools.betterpaths

import java.nio.file.Path
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.xml.bind.annotation.adapters.HexBinaryAdapter

// A collection of hash tools for files.
/**
 * Get the raw bytes of a given [algorithm].
 * This has no protection against a
 * [NoSuchAlgorithmException] being thrown.
 */
fun Path.hashBytes(algorithm: String): ByteArray {
    // Instead of doing MessageDigest.getInstance(algorithm).digest(newInputStream().use { it.readBytes() }),
    // we read bytes in groups to avoid filling RAM or causing other problems.
    return newInputStream().use {
        val digest = MessageDigest.getInstance(algorithm)
        val bytes = ByteArray(8192)
        while (true) {
            val readBytes = it.read(bytes)
            if (readBytes == -1) break
            digest.update(bytes, 0, readBytes)
        }
        digest.digest()
    }
}

/**
 * Get a string hash of the file at this location using [algorithm].
 */
fun Path.hash(algorithm: String): String = HexBinaryAdapter().marshal(hashBytes(algorithm))

/**
 * Get a string hash of the file, or `null` if [algorithm] cannot be found.
 */
fun Path.optionalHash(algorithm: String) = try { hash(algorithm) } catch (e: NoSuchAlgorithmException) { null }

// These three are required in all Java implementations, so they're non-null.
// A NoSuchAlgorithmException thrown on one of these means your Java version is not following the Java specifications.
val Path.sha1:   String get() = hash("SHA-1")
val Path.sha256: String get() = hash("SHA-256")
val Path.md5:    String get() = hash("MD5")

// These are not required in every Java implementation, so they are nullable
// to avoid throwing NoSuchAlgorithmExceptions trying to reference these.
val Path.sha224: String? get() = optionalHash("SHA-224")
val Path.sha384: String? get() = optionalHash("SHA-384")
val Path.sha512: String? get() = optionalHash("SHA-512")
val Path.md2:    String? get() = optionalHash("MD2")

