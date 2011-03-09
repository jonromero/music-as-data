# A language for live programming music in Clojure #

Music as Data (MAD) is a live programming language/environment based on Processing.org written in Clojure.
MAD lets you treat music as data and apply data transformation on the fly so you can experiment with notes and
samples. 

The documentation still lacks a lot of stuff but I am working on it.

# Example #
 
Play a sample or note like this:
  	 (play! [kick])
  
Play two samples (or notes):
	 (play! [kick kick])

Each sample is being played on one time.

If you want to play a sample on the same time:
   	   (play! [kick (+snare hihat)])	

This will play kick on one time and snare+hihat on another.

You can also play triplets:
		(play! [kick [snare snare snare]])

Now kick will be on one time and for the same duration, you'll have three snare.


Of course you can play notes:
      (play! [A4 B4 D#5])

Mix, notes and samples:
	 (play! [hihat A4 kick G6])


The fun begins when you understand that you can manipulate music as data by apply tranformations.
For example:

	(p (reverse (pattern [kick kick])))

Now, instead of using play! (which plays samples in a loop - perfect for building stuff on the fly)
you can create a pattern and then run it only for once using the p function. 
The advantage is that you can manipulate data and their properties thus mess around with note frequences,
tempo, scaling whatever.


# Building #

    lein deps
    lein jar


# Authors #

Designed and developed by Jon Vlachoyiannis (http://jon.is.emotionull.com).