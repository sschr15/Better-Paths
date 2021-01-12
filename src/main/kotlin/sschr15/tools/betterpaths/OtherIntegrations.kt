@file:Suppress("unused")

package sschr15.tools.betterpaths

import java.net.URI
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths

// "/home/user/project/build".toPath()
fun String.toPath(): Path = Paths.get(this)
fun URI.toPath(): Path = Paths.get(this)
fun URL.toPath() = this.toURI().toPath()

// Path.of("/home") / "user" / "project" / "build"
operator fun Path.div(child: String): Path = this.resolve(child)
operator fun Path.div(child: Path): Path = this.resolve(child)
operator fun String.div(child: String): Path = Paths.get(this, child)

// allows similar functionality to File constructors
fun Path(parent: Path, child: String): Path = parent.resolve(child)
fun Path(parent: Path, child: Path): Path = parent.resolve(child)
fun Path(location: String): Path = Paths.get(location)
