(ns music-as-data.mfunctions
  (:use [music-as-data.globals]))

(defn play-element [element]
  ((:play-fn element) element))


(defn play-note [note]
  (.playNote @*outp* (float 0)
			 (float (:duration note))
			 (:data note)))


(defn play-sample [sample]
  (.trigger (:data sample)))


(defn play-chord
  "Executes the play-funcs of of all elements"
  [chord-elements]
  (doseq [element (:data chord-elements)]
	(play-element element)))

