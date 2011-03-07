(ns music-as-data.globals)

(def *samples-path* "/home/darksun4/Sources/clj-processing/examples/data/")

(defn get-samples-path []
  *samples-path*)

(def *minim* (atom nil))
(def *outp* (atom nil))
(def *sine* (atom nil))
(def *pattern* (atom []))


