;; Jon Vlachoyiannis 07/03/2011
;; jon@emotionull.com
(ns music-as-data.core
  (:use [music-as-data.mad]))


(defn start
  "Starts M.A.D."
  []
  (def-notes))

(defn stop
  []
  :ok)

(def derezzed [[E4 G4 E4] [E5 B4 G4 D4 A4 E4 G4 A4]])
(p (pattern derezzed 2))
