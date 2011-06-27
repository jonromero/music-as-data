(ns music-as-data.signalsnotes
  (:use [music-as-data.signals]
		[music-as-data.globals]))

(defrecord Melement [volume pitch duration play-fn data])

(defmacro create-notes [] 
  `(do ~@(for [note '[A B C D D# E F G G#] octave (range 8)] 
              `(defnote ~(symbol (str note octave)) @*sine*))))