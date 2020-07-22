# RouteSeeker_Yeti

This RouteSeeker project is an autonomous generator of autonomous strategy: depending on the actions of our allies, this code 
generates a path that scores as much points for the autonomous as possible. Then, it translates that strategy into the robot code. 
This way, the robot can have as optimal of an autonomous as possible. The path generator is based on machine learning and vector algebra.
The code is adjustable for each new year's game.


To run the project, use src/Main.main()


As of 21st of July, 2020, the current version:
 - Creates a path that avoids walls and uses an entered size of robot cell to successfully navigate on the field
 - Needs around 150,000 trials to reliably derive a strategy that scores 20 points in autonomous
 - Has a graphic interface to enter allied robot's paths, but still does not take their paths into consideration
 - Has simplified physics that doesn't deny paths that may make the robot tip over
 - Has no translation into the robot code


The description of components of the project:
 Menu.py - a graphic interface to enter robot paths.
 /src:
   Cell.java - the field is broken into square sections called cells. Each cell is an instance of Cell class.
   
   Vector.java - a class that works with vectors. This class also contains methods of checking whether the way is clean.
   
   Robot.java - this class is made specifically to describe OUR robot. It includes all kinematic properties and values (such as velocity or current aceleration), the 
sequence of moves, size of the robot cell, amount of cargo and robot's position. This class contains methods that determine where and how long the robot moves.
   
   RobotSequenceRecord.java - used for recording the sequence of positions, accelerations and amounts of cargo robot had during one trial.
   
   Main.java - does the majority of work. It includes the machine learning part of the code, and also describes the field. The principle by which 
it works is the following:

in each cell there is a so-called value-vector that points in the direction of the most probable high-scoring path. If the robot visited this cell, then the value-vector 
gets mutated: depending on how large was the score at the end of the trial, the vector will be set closer (reward) or farther (punishment) from 
the direction the robot moved from this cell. The direction of the vector is set to be the major factor that determines where a robot goes from there
at any given trial, but there is some probability to allow new strategies to appear. This way after a large number of trials, the program will set the vectors to
point in best possible directions (or at least get to a local maximum of posible scores). The program records every best attempt. In the end, the best of the best 
is given back as an output.





      
This project belongs to the FRC Team 3506 Yeti Robotics.
Designed by Grigorii Podoksik
Idea of Caedmon McGinn








