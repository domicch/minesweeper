# minesweeper

A text based minesweeper game written in Kotlin

## To run

Nagivate to /libs folder, exec "java -jar untitled-1.0-SNAPSHOT.jar" in terminal

## Class diagram

https://github.com/domicch/minesweeper/blob/master/diagram/system_design.jpg

## Design concerns

A number of concerns on system design and Kotlin

### Design related

1. The need to use inheritance for Mine and EmptyMine, or just a single Class with isMine() property

Pros:
- Allow creation of new type of item easily

Cons:
- Weak distinction of features between Mine and EmptyMine. Separated mainly for UI display purpose
- Frequent need of distinguishing between mine and not mine with GridItem.getType() and type casts Classes (e.g. Grid)

2. The need to separate View logic into separate class. E.g. Grid -> GridView, vs implement Printable interface directly by model class

Pros:
- Allow model class to be reused by new rendering methods without modifying model classes (e.g. GUI display)

Cons:
- In order for View Classes to access Model Classes data, Model Classes are forced to expose internal data structure (e.g. Game.getGrid(), Grid.getGridItem())

### Kotlin related

1. Which is preferred? Directly expose properties vs use of getter and setter
2. How to expose properties which allow get from external but only set from internal. Current approach is declare property as private var and create getter function
3. Creating 2D array in Kotlin seems particularly complex compared with Java and other data structure of Kotlin
