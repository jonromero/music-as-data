(ns music-as-data.globals)

(def *samples-path* "samples/")

(defn get-samples-path []
  *samples-path*)

(def *minim* (atom nil))
(def *outp* (atom nil))
(def *sine* (atom nil))
(def *pattern* (atom []))
(def *recorder* (atom []))


