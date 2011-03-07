;; Jon Vlachoyiannis 07/03/2011
;; jon@emotionull.com

(ns music-as-data.mad
  (:import [ddf.minim AudioOutput AudioSample])
  (:import (processing.core PApplet PImage PGraphics PFont))
  (:use [music-as-data.globals]
		[music-as-data.mfunctions])
  (:use [rosado.processing]
        [rosado.processing.applet]))


(defn p
  [elements]
  (doseq [element elements]
	(play-element element)
	(Thread/sleep (* (:duration element) 500))))


(defn calc-duration [elements duration count])

;; Maybe a reduce could clean it up more?
(defn pattern
  ([m-element] (pattern m-element 1))
  ([m-element duration]
	 (if (= (type []) (type m-element))
	   (flatten
		(calc-duration m-element duration (count m-element)))
		(assoc m-element :duration duration))))


(defn calc-duration [elements duration count]
  (map #(pattern % (/ duration count))
	   elements))


(defn play!
  ([new-pattern] (play! new-pattern 1))
  ([new-pattern tempo]
  (swap! *pattern* (fn[x] [new-pattern tempo]))))

(defmacro times [num expression]
  ;; small tempo drums demo
  `(doseq [i# (range ~num)]
	~expression))

(defn keep-looping []
  (times 200
		 (p (pattern (first @*pattern*)
					 (second @*pattern*)))))




