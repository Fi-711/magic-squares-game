# Compiling Java Code
## Contents
### Java Classes
#### Core Classes
* GameManager.java
* MagicSquare.java
* Question1b.java
* ScoreManager.java
* UIManager.java
* VFX.java

#### Test Classes - Uses JUnit4 (except UIManager which uses JUnit5)
* DebugGameManager.java - not a test class, created to test GameManager class
* DebugGameManagerTest.java
* MagicSquareTest.java
* MagicSquareParameterizedTest.java  
* ScoreManagerTest.java
* UIManagerTest.java

#### .jar File
* junit-platform-console-standalone-1.8.0-M1.jar

###Running Instructions:
Compile all files with the following code:

> `javac -cp junit-platform-console-standalone-1.8.0-M1.jar *.java`

If you get the following compilation warning:

Note: MagicSquareTest.java uses or overrides a deprecated API.

Note: Recompile with -Xlint:deprecation for details.

Just ignore, it's because I used hamcrest-core library for one test but JUnit 5 is clever enough to be able to run the
tests fine.


### Launch the game:

For **COLOURED** version type into the terminal:

> `java Question1b`

If you are using Windows CMD and have not enabled ANSI colours, add the argument off to **TURN OFF colours**:

> `java Question1b off`

### Run the unit Tests:

> `java -jar junit-platform-console-standalone-1.8.0-M1.jar -cp ./ -c [Test Class File Name]`

e.g. to run MagicSquareTest type:

> `java -jar junit-platform-console-standalone-1.8.0-M1.jar -cp ./ -c MagicSquareTest`

#### Expected Output:
Thanks for using JUnit! Support its development at https://junit.org/sponsoring

.
+-- JUnit Jupiter [OK]

'-- JUnit Vintage [OK]

    '-- MagicSquareTest [OK]

        +-- getMagicSquareFifteenValueAtSevenSeven [OK]

        +-- shuffleMagicSquareThree [OK]

        +-- magicSquareSizeThreeInitializeGrid [OK]

        +-- magicSquareSizeOneInitializeGrid [OK]

        +-- setMagicSquareNineValueAtEightEightUsingArray [OK]

        +-- magicSquareSizeThreeMaxStringWidthOne [OK]

        +-- toStringMagicSquareSizeThree [OK]

        +-- setMagicSquareThreeValueAtOneOneUsingInts [OK]

        +-- magicSquareSizeElevenMaxStringWidthThree [OK]

        +-- getMagicSquareThreeValueAtTwoTwo [OK]

        +-- setMagicSquareThreeValueAtOneOneUsingArray [OK]

        '-- setMagicSquareNineValueAtEightEightUsingInts [OK]

Test run finished after 60 ms

[         3 containers found      ]

[         0 containers skipped    ]

[         3 containers started    ]

[         0 containers aborted    ]

[         3 containers successful ]

[         0 containers failed     ]

[        12 tests found           ]

[         0 tests skipped         ]

[        12 tests started         ]

[         0 tests aborted         ]

[        12 tests successful      ]

[         0 tests failed          ]

### Notes
Game uses ANSI colours which uses escape characters. If running game in windows CMD they may not work natively. I use both Linux and Windows and got ANSI colors working with CMD with a simple reg edit as they became available for Windows 10 a few years back. ANSI colours are supported for Unix/ bash users natively. If you have issues with colours not displaying then turn them off with the argumen "off" - see above.

Powershell causes issues with tabs and thus may display the grid incorrectly. Please use a bash terminal or CMD.

Unit tests were done in Intellij IDEA but can easily be done in the terminal, junit5 jar has been included and the command to run the test is above. 

Most tests use JUnit 4 but the UIManagerTest needs to catch console print outs so uses JUnit 5. The UIManager test may fail on UNIX systems because it is testing print out statements which means testing line endings. Currently, tests are configured for CRLF line feeds.

You can see which Junit tests are being used in the output, if it's under Junit Jupiter then it's JUnit 5 and if it's under Vintage, it's JUnit 4 - most tests were written for JUnit 4.