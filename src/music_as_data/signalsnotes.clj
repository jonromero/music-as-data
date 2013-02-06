(ns music-as-data.signalsnotes
  (:use [music-as-data.signals]
        [music-as-data.globals])
  (:import [music_as_data.elements Melement]))

(defmacro create-notes [] 
  `(do ~@(for [note '[A B C D D# E F G G#] octave (range 8)] 
              `(defnote ~(symbol (str note octave)) @*sine*))))
