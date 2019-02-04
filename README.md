# glife-lib
Conway's Game of Life : [https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life][wikipedia_gol]

### Tf is this ?
This is a java implementation of a Game Of Life computation engine (see link above).
It takes an image or a serialized (.gld) file as input.
Then it can create animated sequences (GIF and APNG), as well as static ones, of multiples generations.

The .gld file format has been created on purpose to save the current state for later use.

### Example
Here's an example of what can be achieved :
![example gif][example_gif]

### Library usage
[![Release][jitpack-badge]][jitpack-url]

Add the JitPack repository to your buildfile (example shows Maven) :
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
Then declare the following dependency :
```xml
<dependency>
    <groupId>com.github.LimeiloN</groupId>
	<artifactId>glife-lib</artifactId>
	<version>1.0.0</version>
</dependency>
```

[wikipedia_gol]: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
[example_gif]: output.gif "Example"
[jitpack-badge]: https://jitpack.io/v/LimeiloN/game-of-life.svg "jitpack badge"
[jitpack-url]: https://jitpack.io/#LimeiloN/game-of-life
