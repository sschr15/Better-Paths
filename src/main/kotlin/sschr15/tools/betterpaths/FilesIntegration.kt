@file:Suppress("unused")

package sschr15.tools.betterpaths

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStream
import java.nio.channels.SeekableByteChannel
import java.nio.charset.Charset
import java.nio.file.*
import java.nio.file.attribute.*
import java.util.stream.*
import kotlin.reflect.KClass

/*
 * Every method / field in this file is a direct path reference to a method
 * in java.nio.file.Files. See that class for documentation.
 */

fun Path.newInputStream(vararg options: OpenOption): InputStream = Files.newInputStream(this, *options)

fun Path.newOutputStream(vararg options: OpenOption): OutputStream = Files.newOutputStream(this, *options)

fun Path.newByteChannel(options: Set<OpenOption>, vararg attrs: FileAttribute<*>): SeekableByteChannel =
    Files.newByteChannel(this, options, *attrs)

fun Path.newByteChannel(vararg options: OpenOption): SeekableByteChannel =
    Files.newByteChannel(this, *options)

fun Path.newDirectoryStream(): DirectoryStream<Path> = Files.newDirectoryStream(this)

fun Path.newDirectoryStream(glob: String): DirectoryStream<Path> = Files.newDirectoryStream(this, glob)

fun Path.newDirectoryStream(filter: DirectoryStream.Filter<in Path>): DirectoryStream<Path> =
    Files.newDirectoryStream(this, filter)

fun Path.createFile(vararg attrs: FileAttribute<*>): Path = Files.createFile(this, *attrs)

fun Path.createDirectory(vararg attrs: FileAttribute<*>): Path = Files.createDirectory(this, *attrs)

fun Path.createDirectories(vararg attrs: FileAttribute<*>): Path = Files.createDirectories(this, *attrs)

fun Path.createTempFile(prefix: String?, suffix: String?, vararg attrs: FileAttribute<*>): Path =
    Files.createTempFile(this, prefix, suffix, *attrs)

fun Path.createTempDirectory(prefix: String?, vararg attrs: FileAttribute<*>): Path =
    Files.createTempDirectory(this, prefix, *attrs)

fun Path.createSymbolicLink(target: Path, vararg attrs: FileAttribute<*>): Path =
    Files.createSymbolicLink(this, target, *attrs)

fun Path.createLink(target: Path): Path = Files.createLink(this, target)

fun Path.delete() = Files.delete(this)

fun Path.deleteIfExists() = Files.deleteIfExists(this)

fun Path.copy(target: Path, vararg options: CopyOption): Path = Files.copy(this, target, *options)

fun Path.move(target: Path, vararg options: CopyOption): Path = Files.move(this, target, *options)

fun Path.readSymbolicLink(): Path = Files.readSymbolicLink(this)

val Path.fileStore: FileStore get() = Files.getFileStore(this)

fun Path.isSameFile(other: Path) = Files.isSameFile(this, other)

val Path.isHidden get() = Files.isHidden(this)

fun Path.probeContentType(): String? = Files.probeContentType(this)

fun <V : FileAttributeView> Path.getFileAttributeView(type: KClass<V>, vararg options: LinkOption): V? =
    Files.getFileAttributeView(this, type.java, *options)

fun <A : BasicFileAttributes> Path.readAttributes(type: KClass<A>, vararg options: LinkOption): A =
    Files.readAttributes(this, type.java, *options)

fun Path.setAttribute(attribute: String, value: Any?, vararg options: LinkOption) {
    Files.setAttribute(this, attribute, value, *options)
}

fun Path.getAttribute(attribute: String, vararg options: LinkOption): Any? =
    Files.getAttribute(this, attribute, *options)

fun Path.readAttribute(attributes: String, vararg options: LinkOption): Map<String, Any?> =
    Files.readAttributes(this, attributes, *options)

fun Path.getPosixFilePermissions(vararg options: LinkOption): Set<PosixFilePermission> =
    Files.getPosixFilePermissions(this, *options)

fun Path.setPosixFilePermissions(perms: Set<PosixFilePermission>): Path =
    Files.setPosixFilePermissions(this, perms)

fun Path.getOwner(vararg options: LinkOption): UserPrincipal = Files.getOwner(this, *options)

fun Path.setOwner(owner: UserPrincipal): Path = Files.setOwner(this, owner)

val Path.isSymbolicLink get() = Files.isSymbolicLink(this)

fun Path.isDirectory(vararg options: LinkOption) = Files.isDirectory(this, *options)

fun Path.isRegularFile(vararg options: LinkOption) = Files.isRegularFile(this, *options)

fun Path.getLastModifiedTime(vararg options: LinkOption): FileTime = Files.getLastModifiedTime(this, *options)

fun Path.setLastModifiedTime(time: FileTime): Path = Files.setLastModifiedTime(this, time)

val Path.size get() = Files.size(this)

fun Path.exists(vararg options: LinkOption) = Files.exists(this, *options)

fun Path.doesNotExist(vararg options: LinkOption) = Files.notExists(this, *options)

val Path.isReadable get() = Files.isReadable(this)

val Path.isWritable get() = Files.isWritable(this)

val Path.isExecutable get() = Files.isExecutable(this)

fun Path.walkFileTree(options: Set<FileVisitOption>, maxDepth: Int, visitor: FileVisitor<in Path>): Path =
    Files.walkFileTree(this, options, maxDepth, visitor)

fun Path.walkFileTree(visitor: FileVisitor<in Path>): Path = Files.walkFileTree(this, visitor)

fun Path.newBufferedReader(cs: Charset): BufferedReader = Files.newBufferedReader(this, cs)

fun Path.newBufferedReader(): BufferedReader = Files.newBufferedReader(this)

fun Path.newBufferedWriter(cs: Charset, vararg options: OpenOption): BufferedWriter =
    Files.newBufferedWriter(this, cs, *options)

fun Path.newBufferedWriter(vararg options: OpenOption): BufferedWriter =
    Files.newBufferedWriter(this, *options)

fun Path.copyFrom(stream: InputStream, vararg options: CopyOption) = Files.copy(stream, this, *options)

fun Path.copyTo(out: OutputStream) = Files.copy(this, out)

fun Path.readAllBytes(): ByteArray = Files.readAllBytes(this)

fun Path.readAllLines(cs: Charset): List<String> = Files.readAllLines(this, cs)

fun Path.readAllLines(): List<String> = Files.readAllLines(this)

fun Path.write(bytes: ByteArray, vararg options: OpenOption): Path = Files.write(this, bytes, *options)

fun Path.write(lines: Iterable<CharSequence>, cs: Charset, vararg options: OpenOption): Path =
    Files.write(this, lines, cs, *options)

fun Path.write(lines: Iterable<CharSequence>, vararg options: OpenOption): Path =
    Files.write(this, lines, *options)

fun Path.list(): Stream<Path> = Files.list(this)

fun Path.walk(maxDepth: Int, vararg options: FileVisitOption): Stream<Path> =
    Files.walk(this, maxDepth, *options)

fun Path.walk(vararg options: FileVisitOption): Stream<Path> =
    Files.walk(this, *options)

fun Path.find(maxDepth: Int, vararg options: FileVisitOption, matcher: (Path, BasicFileAttributes) -> Boolean): Stream<Path> =
    Files.find(this, maxDepth, matcher, *options)

fun Path.lines(cs: Charset): Stream<String> = Files.lines(this, cs)

fun Path.lines(): Stream<String> = Files.lines(this)

/*
 * These four methods below exist in Java 11, but not Java 8.
 * Since this version is meant to compile to Java 8, the
 * implementations found in Java 11 have been recreated.
 */

fun Path.writeString(chars: CharSequence, vararg options: OpenOption): Path = writeString(chars, Charsets.UTF_8, *options)

fun Path.writeString(chars: CharSequence, cs: Charset, vararg options: OpenOption): Path =
    Files.write(this, chars.toString().toByteArray(cs), *options)

fun Path.readString(): String = readString(Charsets.UTF_8)

fun Path.readString(cs: Charset): String = String(Files.readAllBytes(this), cs)
