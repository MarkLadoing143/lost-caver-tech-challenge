# Lost Caver Technical Challenge

## Description
Assumptions:
- The program takes some kind of text input
- The mechanism to accept the input can be almost anything (text file, command line prompt, hard coded input to a unit test….doesn’t really matter, you decide)
- The solution should be complete/done with some sort of feedback to a user that it works correctly against the supplied data.
- You are writing software to save a lost Caver, so write code that you are comfortable sharing with your peers. It’s a good idea to have unit tests…and maybe use a unit testing framework (Junit, pyunit, Nunit, etc).
A Caver (someone that enjoys spelunking) has gotten lost in a cave. The Caver’s LED flash lights are all burned out. Thankfully the Caver still has limited radio communication with their caving partner on the surface. Their partner knows, through the magic of specialized GPS, the current coordinates of the lost Caver. The person on the surface also knows the location of the exit out of the cave and will send specialized instructions down to help the lost caver navigate to safety.
To simplify the communication the instructions are limited to small character instruction set.
- ‘M for move forward one grid point in the cave on the current heading.
- ‘R’ for spin 90 degrees to the right
- ‘L’ for spin 90 degrees to the left.
The partner on the surface knows that the cave is curiously rectangle.
The dimensions of the cave are 20 grid points wide and 16 grid points high.
The bottom left hand corner of the cave is position (0,0).
Input/Output :
The Caver will be given instructions to turn on their specialized GPS. The first input to the program will be the current coordinates and the direction that the Caver is facing. For example:
- (x,y) Direction
o (1,2) N or (4,5) S or (8,8) W or (10, 2) E
The program then should accept a series of instructions and report back on current coordinate and heading (direction) after each instruction.
So if the initial coordinates and direction are : (1,2) N and the next input is ‘M’, the lost Caver should report (1,3) N.
Some potential sample input to a unit test might be : (1,2) N MMMMMMMMRMMMMRMLM
*NOTE*
- The Caver will die if they fall off the edges of the cave (including the top)
- The Caver’s partner at the surface knows where the exit is and will navigate the lost Caver to those coordinates.
- The program doesn’t need to determine whether the caver found the exit successfully
