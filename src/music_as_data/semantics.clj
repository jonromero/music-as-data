(ns music-as-data.semantics
  (:use
        [music-as-data.elements]
        [music-as-data.mfunctions])
  (:import [music_as_data.elements Melement]))

(defn join [& notes]
  (Melement. 0 0 0 play-chord
			 (first (conj [] notes))))

;; Maybe defmulti?
;; + is causing an NPE!
;;(defmacro + [& more]
;; make chords like (+ kick hihat)
;;`(join ~@more))

