# CHIP8-Emulator

This is an academic software project completed for a course on computer architecture. It intends to simulate an 8-bit processor capable of executing instructions from the CHIP-8 language.

Rather than abstract emulation it models physical hardware components and their interactions as accurately as possible to execute CHIP-8 ROMs.

<h2>To run</h2>
 <p>
This project is built with Gradle, install here --> https://gradle.org/<br>
 In the directory containing the build.gradle file run from the command line: <b>gradle build</b><br>
 followed by: <b>gradle run --args=ROM_Name</b><br><br>
  </p>
  
  <h2>Available ROMs</h2>
  <p>
  The ROM_Name field above can be one of the following included ROMs:
   <ul>
     <li>'pong.ch8></li>
     <li>'invaders.ch8></li>
     <li>'tetris.ch8></li>
     <li>'tank.ch8></li>
     <li>'hidden.ch8></li>
     <li>'breakout.ch8></li>
  </ul>
  Or any other proper .ch8 file.<br>
  A proper invocation might be for example: <b>gradle run --args='invaders.ch8'</b>
  </p>
  
  <h2>Contributors</h2>
  <p>
  This project was completed as a team effort with the following contributors:<br>
   - C. Hettrik<br>
   - J. Williams<br>
   - M. Ehrler<br>
  </p>
  
